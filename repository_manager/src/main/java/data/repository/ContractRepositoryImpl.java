package data.repository;

import data.dao.ContractDao;
import data.hib.HibContract;

import java.util.List;

public class ContractRepositoryImpl implements ContractRepository {

    private final ContractDao contractDao;

    public ContractRepositoryImpl(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Override
    public HibContract getContract(int id) {
        return contractDao.get(id);
    }

    @Override
    public List<HibContract> getContracts() {
        return contractDao.get();
    }

    @Override
    public List<HibContract> findContracts(String search) {
        return contractDao.find(search);
    }

    @Override
    public boolean createContract(HibContract contract) {
        return contractDao.create(contract);
    }

    @Override
    public boolean updateContract(HibContract contract) {
        return contractDao.update(contract);
    }

    @Override
    public boolean removeContract(HibContract contract) {
        return contractDao.remove(contract);
    }
}
