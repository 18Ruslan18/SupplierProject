package ui;

import domain.entity.Product;
import domain.entity.Contract;
import domain.entity.Room;
import domain.interactor.product.FindProducts;
import domain.interactor.product.GetProducts;
import domain.interactor.room.*;
import domain.interactor.сontract.*;
import domain.mapper.EntityMapper;
import ui.dialog.ContractNewDialog;
import ui.dialog.RoomNewDialog;
import ui.listener.BackResultListener;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MainForm {
    private JPanel mainPanel;

    private JTabbedPane tabbedPanel;
    private JPanel contractTab;
    private JPanel productTab;
    private JPanel roomPanel;

    private JTextField contractSearchTextField;
    private JButton contractClearButton;
    private JButton contractRemoveButton;
    private JButton contractCreateButton;
    private JButton contractPdfButton;

    private JTextField productSearchTextField;
    private JButton productClearButton;

    private JTextField roomSearchTextField;
    private JButton roomClearButton;
    private JButton roomRemoveButton;
    private JButton roomCreateButton;
    private JButton roomPdfButton;
    private JTable contractTable;
    private JScrollPane contractScrollPanel;
    private JTable productTable;
    private JScrollPane productScrollPanel;
    private JTable roomTable;
    private JScrollPane roomScrollPanel;
    private JButton contractSearchButton;
    private JButton productSearchButton;
    private JButton roomSearchButton;

    private final CreateContract createContract;
    private final FindContracts findContracts;
    private final GetContracts getContracts;
    private final RemoveContract removeContract;
    private final UpdateContract updateContract;
    private final CreateContractPdf createContractPdf;

    private final GetProducts getProducts;
    private final FindProducts findProducts;

    private final CreateRoom createRoom;
    private final FindRoom findRoom;
    private final GetRooms getRooms;
    private final RemoveRoom removeRoom;
    private final UpdateRoom updateRoom;
    private final CreateRoomPdf createRoomPdf;

    private final EntityMapper<Contract, Vector<String>> uiContractMapper;
    private final EntityMapper<Product, Vector<String>> uiProductMapper;
    private final EntityMapper<Room, Vector<String>> uiRoomMapper;

    private DefaultTableModel productModel;

    private boolean isContractDialogOpen = false;
    private boolean isRoomDialogOpen = false;

    public MainForm(CreateContract createContract, FindContracts findContracts, GetContracts getContracts, RemoveContract removeContract, UpdateContract updateContract, CreateContractPdf createContractPdf, GetProducts getProducts, FindProducts findProducts, CreateRoom createRoom, FindRoom findRoom, GetRooms getRooms, RemoveRoom removeRoom, UpdateRoom updateRoom, CreateRoomPdf createRoomPdf, EntityMapper<Contract, Vector<String>> uiContractMapper, EntityMapper<Product, Vector<String>> uiProductMapper, EntityMapper<Room, Vector<String>> uiRoomMapper) {
        this.createContract = createContract;
        this.findContracts = findContracts;
        this.getContracts = getContracts;
        this.removeContract = removeContract;
        this.updateContract = updateContract;
        this.createContractPdf = createContractPdf;

        this.getProducts = getProducts;
        this.findProducts = findProducts;

        this.createRoom = createRoom;
        this.findRoom = findRoom;
        this.getRooms = getRooms;
        this.removeRoom = removeRoom;
        this.updateRoom = updateRoom;
        this.createRoomPdf = createRoomPdf;

        this.uiContractMapper = uiContractMapper;
        this.uiProductMapper = uiProductMapper;
        this.uiRoomMapper = uiRoomMapper;

        initContractTable();
        initProductTable();
        initRoomTable();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void initContractTable() {
        Vector<String> header = new Vector<>();
        header.add("ID");
        header.add("Брэнд");
        header.add("Продукция");
        header.add("Стоимость аренды");
        header.add("Директор");
        header.add("Дата подписания");
        header.add("Дата окончания");

        AtomicBoolean isSearching = new AtomicBoolean(false);
        contractScrollPanel.setViewportView(contractTable);
        DefaultTableModel tableModel = new DefaultTableModel(new Vector<>(getContracts.run().stream().map(uiContractMapper::convert).collect(Collectors.toList())), header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;
                return super.isCellEditable(row, column);
            }
        };
        contractTable.setModel(tableModel);
        contractTable.setFillsViewportHeight(true);
        TableModelListener tableModelListener = e -> {
            if(!isSearching.get() && e.getFirstRow() != -1) updateContract.run(uiContractMapper.deconvert((Vector<String>) tableModel.getDataVector().get(e.getFirstRow())));
        };
        tableModel.addTableModelListener(tableModelListener);

        contractRemoveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int row = contractTable.getSelectedRow();
                tableModel.removeTableModelListener(tableModelListener);
                if (row != -1 && removeContract.run(uiContractMapper.deconvert((Vector<String>) tableModel.getDataVector().get(row))))
                    ((DefaultTableModel) contractTable.getModel()).removeRow(row);
                tableModel.addTableModelListener(tableModelListener);
            }
        });
        contractCreateButton.addActionListener((e) ->
                {
                    if (!isContractDialogOpen) {
                        isContractDialogOpen = true;
                        new ContractNewDialog(new BackResultListener<Vector<String>>() {
                            @Override
                            public void onSuccess(Vector<String> data) {
                                createContract.run(uiContractMapper.deconvert(data));
                                tableModel.setDataVector(new Vector<>(getContracts.run().stream().map(uiContractMapper::convert).collect(Collectors.toList())), header);
                                isContractDialogOpen = false;
                            }

                            @Override
                            public void onFailed() {
                                isContractDialogOpen = false;
                            }
                        });
                    }
                }
        );
        contractClearButton.addActionListener((e) -> {
            contractSearchTextField.setText("");
            tableModel.setDataVector(new Vector<>(getContracts.run().stream().map(uiContractMapper::convert).collect(Collectors.toList())), header);
            isSearching.set(false);
        });
        contractSearchButton.addActionListener((e) -> {
            if(!contractSearchTextField.getText().isEmpty()) {
                isSearching.set(true);
                tableModel.setDataVector(new Vector<>(findContracts.run(contractSearchTextField.getText()).stream().map(uiContractMapper::convert).collect(Collectors.toList())), header);
            } else {
                tableModel.setDataVector(new Vector<>(getContracts.run().stream().map(uiContractMapper::convert).collect(Collectors.toList())), header);
                isSearching.set(false);
            }
        });
        contractPdfButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                createContractPdf.run();
            }
        });
    }


    private void initRoomTable() {
        Vector<String> header = new Vector<>();
        header.addElement("ID");
        header.addElement("Помещение");
        header.addElement("Наниматель");
        header.addElement("Температура");
        header.addElement("Продукция");

        AtomicBoolean isSearching = new AtomicBoolean(false);
        roomScrollPanel.setViewportView(roomTable);
        DefaultTableModel tableModel = new DefaultTableModel(new Vector<>(getRooms.run().stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;
                return super.isCellEditable(row, column);
            }
        };
        roomTable.setModel(tableModel);
        roomTable.setFillsViewportHeight(true);
        TableModelListener tableModelListener = e -> {
            if(!isSearching.get() && e.getFirstRow() != -1) {
                updateRoom.run(uiRoomMapper.deconvert((Vector<String>) tableModel.getDataVector().get(e.getFirstRow())));
                updateProductTable();
            }
        };
        tableModel.addTableModelListener(tableModelListener);

        roomRemoveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int row = roomTable.getSelectedRow();
                tableModel.removeTableModelListener(tableModelListener);
                if (row != -1 && removeRoom.run(uiRoomMapper.deconvert((Vector<String>) tableModel.getDataVector().get(row))))
                    ((DefaultTableModel) roomTable.getModel()).removeRow(row);
                updateProductTable();
                tableModel.addTableModelListener(tableModelListener);
            }
        });
        roomCreateButton.addActionListener((e) ->
                {
                    if (!isRoomDialogOpen) {
                        isRoomDialogOpen = true;
                        new RoomNewDialog(new BackResultListener<Vector<String>>() {
                            @Override
                            public void onSuccess(Vector<String> data) {
                                createRoom.run(uiRoomMapper.deconvert(data));
                                tableModel.setDataVector(new Vector<>(getRooms.run().stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
                                isRoomDialogOpen = false;
                                updateProductTable();
                            }

                            @Override
                            public void onFailed() {
                                isRoomDialogOpen = false;
                            }
                        });
                    }
                }
        );
        roomClearButton.addActionListener((e) -> {
            roomSearchTextField.setText("");
            tableModel.setDataVector(new Vector<>(getRooms.run().stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
            isSearching.set(false);
        });
        roomSearchTextField.addActionListener((e) -> {
            if(!roomSearchTextField.getText().isEmpty()) {
                isSearching.set(true);
                tableModel.setDataVector(new Vector<>(findRoom.run(roomSearchTextField.getText()).stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
            } else {
                tableModel.setDataVector(new Vector<>(getRooms.run().stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
                isSearching.set(false);
            }
        });
        roomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (roomTable.getSelectedColumn() == 5) {
                    String text = textAreaDialog(null, roomTable.getValueAt(roomTable.getSelectedRow(), roomTable.getSelectedColumn()).toString(), "Заключение");
                    if (text != null) {
                        roomTable.setValueAt(text, roomTable.getSelectedRow(), roomTable.getSelectedColumn());
                    }
                }
            }
        });
        roomSearchButton.addActionListener((e) -> {
            if(!roomSearchTextField.getText().isEmpty()) {
                isSearching.set(true);
                tableModel.setDataVector(new Vector<>(findRoom.run(roomSearchTextField.getText()).stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
            } else {
                tableModel.setDataVector(new Vector<>(getRooms.run().stream().map(uiRoomMapper::convert).collect(Collectors.toList())), header);
                isSearching.set(false);
            }
        });
        roomPdfButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                createRoomPdf.run();
            }
        });
    }

    private void initProductTable() {
        Vector<String> header = new Vector<>(2);
        header.addElement("Продукция");
        header.addElement("Кол-во занятых товаром помещений");
        header.addElement("Общий процент заполненности склада товаром");

        productScrollPanel.setViewportView(productTable);
        productModel = new DefaultTableModel(new Vector<>(getProducts.run().stream().map(uiProductMapper::convert).collect(Collectors.toList())), header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) return false;
                return super.isCellEditable(row, column);
            }
        };
        productTable.setModel(productModel);
        productTable.setFillsViewportHeight(true);

        AtomicBoolean isSearching = new AtomicBoolean(false);
        productClearButton.addActionListener((e) -> {
            productSearchTextField.setText("");
            productModel.setDataVector(new Vector<>(getProducts.run().stream().map(uiProductMapper::convert).collect(Collectors.toList())), header);
            isSearching.set(false);
        });
        productSearchButton.addActionListener((e) -> {
            if(!productSearchTextField.getText().isEmpty()) {
                isSearching.set(true);
                productModel.setDataVector(new Vector<>(findProducts.run(productSearchTextField.getText()).stream().map(uiProductMapper::convert).collect(Collectors.toList())), header);
            } else {
                productModel.setDataVector(new Vector<>(getProducts.run().stream().map(uiProductMapper::convert).collect(Collectors.toList())), header);
                isSearching.set(false);
            }
        });
    }

    private void updateProductTable() {
        Vector<String> header = new Vector<>(2);
        header.addElement("Продукция");
        header.addElement("Кол-во занятых товаром помещений");
        header.addElement("Общий процент заполненности склада товаром");

        productModel.setDataVector(new Vector<>(getProducts.run().stream().map(uiProductMapper::convert).collect(Collectors.toList())), header);
    }

    private String textAreaDialog(Object obj, String text, String title) {
        JTextArea textArea = new JTextArea(text);
        textArea.setColumns(30);
        textArea.setRows(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(textArea.getPreferredSize().width, textArea.getPreferredSize().height);
        int ret = JOptionPane.showConfirmDialog((Component) obj, new JScrollPane(textArea), title, JOptionPane.OK_OPTION);
        if (ret == 0) {
            return textArea.getText();
        } else {
            return null;
        }
    }
}
