package domain.interactor.room;

import data.hib.HibRoom;
import data.repository.RoomRepository;
import domain.entity.Room;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class FindRoom extends BaseUseCase<List<Room>, String> {

    private final RoomRepository roomRepository;
    private final EntityMapper<HibRoom, Room> roomMapper;

    public FindRoom(RoomRepository roomRepository, EntityMapper<HibRoom, Room> roomMapper) {
        super(Logger.getLogger(FindRoom.class.getName()));
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<Room> run(String search) {
        List<Room> result = roomRepository.findRooms(search).stream().map(roomMapper::convert).collect(Collectors.toList());
        logInfo(search, result.toString());
        return result;
    }
}
