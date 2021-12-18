package domain.interactor.room;

import data.hib.HibRoom;
import data.repository.RoomRepository;
import domain.entity.Room;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.logging.Logger;


public class CreateRoom extends BaseUseCase<Boolean, Room> {

    private final RoomRepository roomRepository;
    private final EntityMapper<HibRoom, Room> roomMapper;

    public CreateRoom(RoomRepository roomRepository, EntityMapper<HibRoom, Room> roomMapper) {
        super(Logger.getLogger(CreateRoom.class.getName()));
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Boolean run(Room params) {
        Boolean result = roomRepository.createRoom(roomMapper.deconvert(params));
        logInfo(params.toString(), result.toString());
        return result;
    }
}
