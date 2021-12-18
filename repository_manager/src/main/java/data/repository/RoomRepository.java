package data.repository;

import data.hib.HibRoom;

import java.util.List;

/**
 * Репозиторий для работы с Room
 */

public interface RoomRepository {

    HibRoom getRoom(int id);

    List<HibRoom> getRooms();

    List<HibRoom> findRooms(String search);

    boolean createRoom(HibRoom room);

    boolean updateRoom(HibRoom room);

    boolean removeRoom(HibRoom room);
}
