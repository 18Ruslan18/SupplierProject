package ui.mapper;

import domain.entity.Contract;
import domain.mapper.EntityMapper;

import java.util.Vector;

public class UIContractMapper implements EntityMapper<Contract, Vector<String>> {

    @Override
    public Vector<String> convert(Contract contract) {
        Vector<String> vector = new Vector<>(10);
        vector.add(String.valueOf(contract.getId()));
        vector.add(contract.getBrand());
        vector.add(contract.getBusiness());
        vector.add(contract.getRent());
        vector.add(contract.getDirector());
        vector.add(contract.getOndate());
        vector.add(contract.getOffdate());
        return vector;
    }

    @Override
    public Contract deconvert(Vector<String> vector) {
        if(vector.size() == 6) {
            return new Contract(
                    vector.get(0),
                    vector.get(1),
                    vector.get(2),
                    vector.get(3),
                    vector.get(4),
                    vector.get(5)
            );
        } else {
            return new Contract(
                    Integer.parseInt(vector.get(0)),
                    vector.get(1),
                    vector.get(2),
                    vector.get(3),
                    vector.get(4),
                    vector.get(5),
                    vector.get(6)
            );
        }
    }
}
