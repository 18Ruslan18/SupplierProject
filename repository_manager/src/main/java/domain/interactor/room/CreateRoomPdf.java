package domain.interactor.room;

import domain.entity.Room;
import domain.interactor.BaseUseCase;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRProperties;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class CreateRoomPdf extends BaseUseCase<Boolean, Void> {

    private static final String path = "rooms.pdf";

    private final GetRooms getRooms;

    public CreateRoomPdf(GetRooms getRooms) {
        super(Logger.getLogger(CreateRoomPdf.class.getName()));
        this.getRooms = getRooms;
    }

    @Override
    public Boolean run(Void params) {
        try {
            List<Room> rooms = getRooms.run();
            File style = ResourceUtils.getFile("classpath:room.jrxml");
            JasperReport report = JasperCompileManager.compileReport(style.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rooms);
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
