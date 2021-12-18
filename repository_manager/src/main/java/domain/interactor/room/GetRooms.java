package domain.interactor.room;

import data.hib.HibRoom;
import data.repository.RoomRepository;
import domain.entity.Room;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class GetRooms extends BaseUseCase<List<Room>, Void> {

    private final RoomRepository roomRepository;
    private final EntityMapper<HibRoom, Room> roomMapper;

    public GetRooms(RoomRepository roomRepository, EntityMapper<HibRoom, Room> roomMapper) {
        super(Logger.getLogger(GetRooms.class.getName()));
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public List<Room> run(Void params) {
        List<Room> result = roomRepository.getRooms().stream().map(roomMapper::convert).collect(Collectors.toList());
        logInfo("null", result.toString());
        return result;
    }
}
