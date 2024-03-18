package cep;

import Atxy2k.CustomTextField.RestrictedTextField;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

public class Screen extends JFrame {
    private ImageIcon home = new ImageIcon("src/img/home.png");
    private JTextField cepField;
    private JTextField enderField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JComboBox<String> ufComboBox;


    public Screen() {

        setTitle("Buscar CEP");
        setIconImage(home.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 400, 300);
        setVisible(true);
        setLayout(null);

        JButton aboutIcon = new JButton();
        ImageIcon about = new ImageIcon("src/img/about.png");
        aboutIcon.setIcon(about);
        aboutIcon.setToolTipText("Sobre");
        aboutIcon.setBorder(null);
        aboutIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutIcon.setBounds(330, 5, 50, 50);
        aboutIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenAbout screenAbout = new ScreenAbout();
                screenAbout.setVisible(true);
            }
        });


        JLabel cepLabel = new JLabel("CEP");
        cepLabel.setBounds(40, 13, 100, 25);


        cepField = new JTextField();
        cepField.setBounds(80, 13, 100, 25);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(190, 13, 100, 25);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cepField.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Um CEP contém 8 digitos ");
                    cepField.requestFocus();
                } else {
                    buscarCep();
                }
            }
        });

        JLabel enderLabel = new JLabel("Endereço");
        enderLabel.setBounds(45, 75, 100, 25);

        enderField = new JTextField();
        enderField.setBounds(120, 75, 200, 25);

        JLabel bairroLabel = new JLabel("Bairro");
        bairroLabel.setBounds(45, 110, 100, 25);

        bairroField = new JTextField();
        bairroField.setBounds(120, 110, 200, 25);

        JLabel cidadeLabel = new JLabel("Cidade");
        cidadeLabel.setBounds(45, 145, 100, 25);

        cidadeField = new JTextField();
        cidadeField.setBounds(120, 145, 150, 25);

        JLabel ufLabel = new JLabel("UF");
        ufLabel.setBounds(290, 145, 100, 25);


        String[] estado = {"  ", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        ufComboBox = new JComboBox<>(estado);
        ufComboBox.setBounds(310, 145, 50, 25);


        JButton limparButton = new JButton("Limpar");
        limparButton.setBounds(20, 200, 100, 25);
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
            }
        });

        add(aboutIcon);
        add(buscarButton);
        add(cepLabel);
        add(cepField);
        add(enderLabel);
        add(enderField);
        add(bairroLabel);
        add(bairroField);
        add(cidadeLabel);
        add(cidadeField);
        add(ufLabel);
        add(ufComboBox);
        add(limparButton);

        RestrictedTextField validar = new RestrictedTextField(cepField);
        validar.setOnlyNums(true);
        validar.setLimit(8);
    }//final construtor

    private void buscarCep() {
        String logradouro = "";
        String tipoLogradouro = "";
        String resultado = null;
        String cep = cepField.getText();

        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            SAXReader xml = new SAXReader();
            Document document = xml.read(url);
            Element root = document.getRootElement();
            for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
                Element element = it.next();
                if (element.getQualifiedName().equals("cidade")) {
                    cidadeField.setText(element.getText());
                }
                if (element.getQualifiedName().equals("bairro")) {
                    bairroField.setText(element.getText());
                }
                if (element.getQualifiedName().equals("uf")) {
                    ufComboBox.setSelectedItem(element.getText());
                }
                if (element.getQualifiedName().equals("tipo_logradouro")) {
                    tipoLogradouro = element.getText();
                }
                if (element.getQualifiedName().equals("logradouro")) {
                    logradouro = element.getText();
                }
                if (element.getQualifiedName().equals("resultado")) {
                    resultado = element.getText();
                    if (resultado.equals("1")) {

                    } else {
                        JOptionPane.showMessageDialog(null, "CEP não encontrado.");
                    }
                }
            }
            //setar o campo endereco
            enderField.setText(tipoLogradouro + " " + logradouro);
        } catch (Exception e) {
            System.out.println("e");
        }

    }

    private void limpar() {
        cepField.setText(null);
        enderField.setText(null);
        bairroField.setText(null);
        cidadeField.setText(null);
        ufComboBox.setSelectedItem(null);
    }

}


