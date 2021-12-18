package domain.interactor.product;

import data.repository.RoomRepository;
import domain.entity.Product;
import domain.entity.Room;
import domain.interactor.BaseUseCase;
import domain.mapper.RoomMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class GetProducts extends BaseUseCase<List<Product>, Void> {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public GetProducts(RoomRepository roomRepository, RoomMapper roomMapper) {
        super(Logger.getLogger(GetProducts.class.getName()));
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }



    @Override
    public List<Product> run(Void params) {
        List<Room> rooms = roomRepository.getRooms().stream().map(roomMapper::convert).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        List<String> goods = new ArrayList<>();

        rooms.forEach((p) -> goods.add(p.getGoods()));
        goods.forEach((d -> {
            if (products.stream().map(Product::getGoods).collect(Collectors.toList()).contains(d)) {
                products.forEach((di) -> {
                    if (di.getGoods().equals(d)) {
                        di.setCount(di.getCount() + 1);
                    }
                });
            } else {
                products.add(new Product(d, 1));
            }
        }));

        logInfo("null", products.toString());
        return products;
    }
}
