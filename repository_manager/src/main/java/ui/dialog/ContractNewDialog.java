package ui.dialog;

import ui.listener.BackResultListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class ContractNewDialog extends JDialog {

    private JPanel content;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField brandTextField;
    private JTextField businessTextField;
    private JTextField rentTextField;
    private JTextField directorTextField;
    private JTextField ondateTextField;
    private JTextField offdateTextField;
    private JLabel errorLable;

    private BackResultListener<Vector<String>> backResultListener;

    public ContractNewDialog(BackResultListener<Vector<String>> backResultListener) {
        this.backResultListener = backResultListener;
        Image image = Toolkit.getDefaultToolkit().getImage(".idea/inv_5.png");
        setIconImage(image);
        setPreferredSize(new Dimension(640, 480));
        setMaximumSize(new Dimension(640, 480));
        pack();
        setVisible(true);
        setContentPane(content);
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

        content.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                errorLable.setVisible(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                errorLable.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                errorLable.setVisible(false);
            }
        };
        brandTextField.getDocument().addDocumentListener(documentListener);
        businessTextField.getDocument().addDocumentListener(documentListener);
        rentTextField.getDocument().addDocumentListener(documentListener);
        directorTextField.getDocument().addDocumentListener(documentListener);
        ondateTextField.getDocument().addDocumentListener(documentListener);
        offdateTextField.getDocument().addDocumentListener(documentListener);
    }

    private void onOK() {
        if (brandTextField.getText().isEmpty() ||
                businessTextField.getText().isEmpty() ||
                rentTextField.getText().isEmpty() ||
                directorTextField.getText().isEmpty() ||
                ondateTextField.getText().isEmpty() ||
                offdateTextField.getText().isEmpty()) {
            errorLable.setVisible(true);
            errorLable.setText("<html><font color='red'>Поля не заполнены!</font></html>\"");
            return;
        }
        Vector<String> vector = new Vector<>();
        vector.addElement(brandTextField.getText());
        vector.addElement(businessTextField.getText());
        vector.addElement(rentTextField.getText());
        vector.addElement(directorTextField.getText());
        vector.addElement(ondateTextField.getText());
        vector.addElement(offdateTextField.getText());
        backResultListener.onSuccess(vector);
        dispose();
    }

    private void onCancel() {
        backResultListener.onFailed();
        dispose();
    }
}
