package domain.interactor.сontract;

import data.hib.HibContract;
import data.repository.ContractRepository;
import domain.entity.Contract;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class FindContracts extends BaseUseCase<List<Contract>, String> {

    private final ContractRepository contractRepository;
    private final EntityMapper<HibContract, Contract> contractMapper;

    public FindContracts(ContractRepository contractRepository, EntityMapper<HibContract, Contract> contractMapper) {
        super(Logger.getLogger(FindContracts.class.getName()));
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    @Override
    public List<Contract> run(String search) {
        List<Contract> result = contractRepository.findContracts(search).stream().map(contractMapper::convert).collect(Collectors.toList());
        logInfo(search, result.toString());
        return result;
    }
}