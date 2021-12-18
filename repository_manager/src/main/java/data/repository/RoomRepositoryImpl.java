package data.repository;

import data.dao.RoomDao;
import data.hib.HibRoom;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private final RoomDao roomDao;

    public RoomRepositoryImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public HibRoom getRoom(int id) {
        return roomDao.get(id);
    }

    @Override
    public List<HibRoom> getRooms() {
        return roomDao.get();
    }

    @Override
    public List<HibRoom> findRooms(String search) {
        return roomDao.find(search);
    }

    @Override
    public boolean createRoom(HibRoom room) {
        return roomDao.create(room);
    }

    @Override
    public boolean updateRoom(HibRoom room) {
        return roomDao.update(room);
    }

    @Override
    public boolean removeRoom(HibRoom room) {
        return roomDao.remove(room);
    }
}
