package domain.interactor.сontract;

import data.hib.HibContract;
import data.repository.ContractRepository;
import domain.entity.Contract;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class GetContracts extends BaseUseCase<List<Contract>, Void> {

    private final ContractRepository contractRepository;
    private final EntityMapper<HibContract, Contract> contractMapper;

    public GetContracts(ContractRepository contractRepository, EntityMapper<HibContract, Contract> contractMapper) {
        super(Logger.getLogger(GetContracts.class.getName()));
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    @Override
    public List<Contract> run(Void params) {
        List<Contract> result = contractRepository.getContracts().stream().map(contractMapper::convert).collect(Collectors.toList());
        logInfo("null", result.toString());
        return result;
    }
}
