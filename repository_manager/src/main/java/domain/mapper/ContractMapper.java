package domain.mapper;

import data.hib.HibContract;
import domain.entity.Contract;

public class ContractMapper implements EntityMapper<HibContract, Contract> {

    @Override
    public Contract convert(HibContract hib) {
        return new Contract(
                hib.getId(),
                hib.getBrand(),
                hib.getBusiness(),
                hib.getRent(),
                hib.getDirector(),
                hib.getOndate(),
                hib.getOffdate()
        );
    }

    @Override
    public HibContract deconvert(Contract pojo) {
        return new HibContract(
                pojo.getId(),
                pojo.getBrand(),
                pojo.getBusiness(),
                pojo.getRent(),
                pojo.getDirector(),
                pojo.getOndate(),
                pojo.getOffdate()
        );
    }
}
