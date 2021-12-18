package domain.interactor.сontract;

import domain.entity.Contract;
import domain.interactor.BaseUseCase;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class CreateContractPdf extends BaseUseCase<Boolean, Void> {

    private static final String path = "contracts.pdf";

    private final GetContracts getContracts;

    public CreateContractPdf(GetContracts getContracts) {
        super(Logger.getLogger(CreateContractPdf.class.getName()));
        this.getContracts = getContracts;
    }

    @Override
    public Boolean run(Void params) {
        try {
            List<Contract> patients = getContracts.run();
            File style = ResourceUtils.getFile("classpath:contract.jrxml");
            JasperReport report = JasperCompileManager.compileReport(style.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patients);
            JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);
            JasperExportManager.exportReportToPdfFile(print, path);
            logInfo("null", "true");
            return true;
        } catch (Exception e) {
            logError(e);
            return false;
        }
    }
}
