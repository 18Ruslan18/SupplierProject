package domain.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RoomTest {

    private Room room;

    @Before
    public void init() {
        room = new Room(
                "A21",
                "Adidas",
                "36",
                "Shoes"
        );
    }
}