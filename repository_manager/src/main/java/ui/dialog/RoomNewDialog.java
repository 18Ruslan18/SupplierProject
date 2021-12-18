package ui.dialog;

import ui.listener.BackResultListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class RoomNewDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea protocolTextArea;
    private JTextField spaceTextField;
    private JTextField companyTextField;
    private JTextField tempTextField;
    private JTextField goodsTextField;
    private JLabel errorLabel;

    private BackResultListener<Vector<String>> backResultListener;

    public RoomNewDialog(BackResultListener<Vector<String>> backResultListener) {
        this.backResultListener = backResultListener;
        Image image = Toolkit.getDefaultToolkit().getImage(".idea/inv_5.png");
        setIconImage(image);
        setPreferredSize(new Dimension(640, 480));
        setMaximumSize(new Dimension(640, 480));
        pack();
        setVisible(true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                errorLabel.setVisible(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                errorLabel.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                errorLabel.setVisible(false);
            }
        };
        spaceTextField.getDocument().addDocumentListener(documentListener);
        companyTextField.getDocument().addDocumentListener(documentListener);
        tempTextField.getDocument().addDocumentListener(documentListener);
        goodsTextField.getDocument().addDocumentListener(documentListener);
    }

    private void onOK() {
        if (spaceTextField.getText().isEmpty() ||
                companyTextField.getText().isEmpty() ||
                tempTextField.getText().isEmpty() ||
                goodsTextField.getText().isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText("<html><font color='red'>Поля не заполнены!</font></html>\"");
            return;
        }
        Vector<String> vector = new Vector<>();
        vector.addElement(spaceTextField.getText());
        vector.addElement(companyTextField.getText());
        vector.addElement(tempTextField.getText());
        vector.addElement(goodsTextField.getText());
        backResultListener.onSuccess(vector);
        dispose();
    }

    private void onCancel() {
        backResultListener.onFailed();
        dispose();
    }
}
