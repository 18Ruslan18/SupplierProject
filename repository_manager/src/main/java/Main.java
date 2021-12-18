import data.dao.ContractDao;
import data.dao.RoomDao;
import data.hib.HibContract;
import data.hib.HibRoom;
import data.repository.ContractRepository;
import data.repository.ContractRepositoryImpl;
import data.repository.RoomRepository;
import data.repository.RoomRepositoryImpl;
import domain.entity.Product;
import domain.entity.Contract;
import domain.entity.Room;
import domain.interactor.product.FindProducts;
import domain.interactor.product.GetProducts;
import domain.interactor.сontract.*;
import domain.interactor.room.*;
import domain.mapper.ContractMapper;
import domain.mapper.EntityMapper;
import domain.mapper.RoomMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ui.MainForm;
import ui.mapper.UIProductMapper;
import ui.mapper.UIContractMapper;
import ui.mapper.UIRoomMapper;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            //
        }
        ContractDao contractDao = new ContractDao(sessionFactory);
        RoomDao roomDao = new RoomDao(sessionFactory);

        ContractRepository contractRepository = new ContractRepositoryImpl(contractDao);
        RoomRepository roomRepository = new RoomRepositoryImpl(roomDao);

        EntityMapper<HibContract, Contract> contractMapper = new ContractMapper();
        CreateContract createContract = new CreateContract(contractRepository, contractMapper);
        FindContracts findContracts = new FindContracts(contractRepository, contractMapper);
        GetContracts getContracts = new GetContracts(contractRepository, contractMapper);
        RemoveContract removeContract = new RemoveContract(contractRepository, contractMapper);
        UpdateContract updateContract = new UpdateContract(contractRepository, contractMapper);
        CreateContractPdf createContractPdf = new CreateContractPdf(getContracts);

        GetProducts getProducts = new GetProducts(roomRepository, new RoomMapper());
        FindProducts findProducts = new FindProducts(getProducts);

        EntityMapper<HibRoom, Room> roomMapper = new RoomMapper();
        CreateRoom createRoom = new CreateRoom(roomRepository, roomMapper);
        FindRoom findRoom = new FindRoom(roomRepository, roomMapper);
        GetRooms getRooms = new GetRooms(roomRepository, roomMapper);
        RemoveRoom removeRoom = new RemoveRoom(roomRepository, roomMapper);
        UpdateRoom updateRoom = new UpdateRoom(roomRepository, roomMapper);
        CreateRoomPdf createRoomPdf = new CreateRoomPdf(getRooms);

        EntityMapper<Contract, Vector<String>> uiContractMapper = new UIContractMapper();
        EntityMapper<Product, Vector<String>> uiProductMapper = new UIProductMapper();
        EntityMapper<Room, Vector<String>> uiRoomMapper = new UIRoomMapper();

        JFrame main = new JFrame("Repository 135");
        MainForm form = new MainForm(
                createContract,
                findContracts,
                getContracts,
                removeContract,
                updateContract,
                createContractPdf,
                getProducts,
                findProducts,
                createRoom,
                findRoom,
                getRooms,
                removeRoom,
                updateRoom,
                createRoomPdf,
                uiContractMapper,
                uiProductMapper,
                uiRoomMapper
        );
        Image image = Toolkit.getDefaultToolkit().getImage(".idea/inv_5.png");
        main.setIconImage(image);
        main.setContentPane(form.getMainPanel());
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setPreferredSize(new Dimension(1000, 500));
        main.setMinimumSize(new Dimension(1000, 500));
        main.pack();
        main.setVisible(true);
    }
}
