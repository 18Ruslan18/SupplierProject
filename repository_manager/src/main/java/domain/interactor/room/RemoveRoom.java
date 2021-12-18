package domain.interactor.room;

import data.hib.HibRoom;
import data.repository.RoomRepository;
import domain.entity.Room;
import domain.interactor.BaseUseCase;
import domain.mapper.EntityMapper;

import java.util.logging.Logger;


public class RemoveRoom extends BaseUseCase<Boolean, Room> {

    private final RoomRepository roomRepository;
    private final EntityMapper<HibRoom, Room> roomMapper;

    public RemoveRoom(RoomRepository roomRepository, EntityMapper<HibRoom, Room> roomMapper) {
        super(Logger.getLogger(RemoveRoom.class.getName()));
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Boolean run(Room params) {
        Boolean result = roomRepository.removeRoom(roomMapper.deconvert(params));
        logInfo(params.toString(), result.toString());
        return result;
    }
}
