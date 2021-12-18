package ui.mapper;

import domain.entity.Room;
import domain.mapper.EntityMapper;

import java.util.Vector;

public class UIRoomMapper implements EntityMapper<Room, Vector<String>> {

    @Override
    public Vector<String> convert(Room room) {
        Vector<String> vector = new Vector<>();
        vector.add(String.valueOf(room.getId()));
        vector.add(room.getSpace());
        vector.add(room.getCompany());
        vector.add(room.getTemp());
        vector.add(room.getGoods());
        return vector;
    }

    @Override
    public Room deconvert(Vector<String> vector) {
        if (vector.size() == 4) {
            return new Room(
                    vector.get(0),
                    vector.get(1),
                    vector.get(2),
                    vector.get(3)
            );
        } else {
            return new Room(
                    Integer.parseInt(vector.get(0)),
                    vector.get(1),
                    vector.get(2),
                    vector.get(3),
                    vector.get(4)
            );
        }
    }
}
