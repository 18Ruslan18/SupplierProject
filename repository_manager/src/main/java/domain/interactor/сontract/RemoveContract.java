package domain.interactor.сontract;

import data.hib.HibContract;
import data.repository.ContractRepository;
import domain.entity.Contract;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.logging.Logger;


public class RemoveContract extends BaseUseCase<Boolean, Contract> {

    private final ContractRepository contractRepository;
    private final EntityMapper<HibContract, Contract> contractMapper;

    public RemoveContract(ContractRepository contractRepository, EntityMapper<HibContract, Contract> contractMapper) {
        super(Logger.getLogger(RemoveContract.class.getName()));
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    @Override
    public Boolean run(Contract params) {
        Boolean result = contractRepository.removeContract(contractMapper.deconvert(params));
        logInfo(params.toString(), result.toString());
        return result;
    }
}
