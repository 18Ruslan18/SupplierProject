package domain.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ContractTest {

    private Contract contract;

    @Before
    public void init() {
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

}