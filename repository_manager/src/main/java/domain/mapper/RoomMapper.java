package domain.mapper;

import data.hib.HibRoom;
import domain.entity.Room;

public class RoomMapper implements EntityMapper<HibRoom, Room> {

    @Override
    public Room convert(HibRoom hib) {
        return new Room(
                hib.getId(),
                hib.getSpace(),
                hib.getCompany(),
                hib.getTemp(),
                hib.getGoods()
        );
    }

    @Override
    public HibRoom deconvert(Room pojo) {
        return new HibRoom(
                pojo.getId(),
                pojo.getSpace(),
                pojo.getCompany(),
                pojo.getTemp(),
                pojo.getGoods()
        );
    }
}
