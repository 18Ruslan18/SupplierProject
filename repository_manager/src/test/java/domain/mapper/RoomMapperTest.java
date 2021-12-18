package domain.mapper;

import data.hib.HibRoom;
import domain.entity.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RoomMapperTest {

    private EntityMapper<HibRoom, Room> roomMapper;
    private HibRoom hibRoom;
    private Room room;

    @Before
    public void init() {
        roomMapper = new RoomMapper();

        hibRoom = new HibRoom(
                "A21",
                "Adidas",
                "36",
                "Shoes"
        );
        room = new Room(
                "A21",
                "Adidas",
                "36",
                "Shoes"
        );
    }

    @Test
    public void convertPojoToHib() {
        Assert.assertNotNull(roomMapper.deconvert(room));
        Assert.assertEquals(roomMapper.deconvert(room), hibRoom);
    }

    @Test
    public void convertHibToPojo() {
        Assert.assertNotNull(roomMapper.convert(hibRoom));
        Assert.assertEquals(roomMapper.convert(hibRoom), room);
    }
}