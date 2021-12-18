package domain.mapper;

import data.hib.HibContract;
import domain.entity.Contract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ContractMapperTest {

    private EntityMapper<HibContract, Contract> contractMapper;
    private HibContract hibContract;
    private Contract contract;

    @Before
    public void init() {
        contractMapper = new ContractMapper();

        hibContract = new HibContract(
                0,
                "Adidas",
                "Shoes",
                "150000",
                "Jimmy Paul",
                "12.12.2020",
                "12.11.2022"
        );
        contract = new Contract(
                0,
                "Adidas",
                "Shoes",
                "150000",
                "Jimmy Paul",
                "12.12.2020",
                "12.11.2022"
        );
    }

    @Test
    public void convertPojoToHib() {
        Assert.assertNotNull(contractMapper.deconvert(contract));
        Assert.assertEquals(contractMapper.deconvert(contract), hibContract);
    }

    @Test
    public void convertHibToPojo() {
        Assert.assertNotNull(contractMapper.convert(hibContract));
        Assert.assertEquals(contractMapper.convert(hibContract), contract);
    }
}