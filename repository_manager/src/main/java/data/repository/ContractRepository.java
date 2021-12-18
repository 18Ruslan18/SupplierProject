package data.repository;

import data.hib.HibContract;

import java.util.List;

/**
 * Репозиторий для работы с Contract
 */

public interface ContractRepository {

    HibContract getContract(int id);

    List<HibContract> getContracts();

    List<HibContract> findContracts(String search);

    boolean createContract(HibContract contract);

    boolean updateContract(HibContract contract);

    boolean removeContract(HibContract contract);
}
