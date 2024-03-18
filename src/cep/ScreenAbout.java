package cep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class ScreenAbout extends JFrame {
    private ImageIcon home = new ImageIcon("src/img/home.png");

    private ImageIcon about = new ImageIcon("src/img/about.png");

    public ScreenAbout() {

        setTitle("Sobre");
        setIconImage(home.getImage());
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(150, 150, 400, 300);
        setVisible(true);
        setLayout(null);


        JLabel versionLabel = new JLabel("Buscar CEP - Version 1.");
        versionLabel.setBounds(50, 25, 160, 25);

        JLabel authorLabel = new JLabel("@author Bruno Portilho");
        authorLabel.setBounds(50, 75, 160, 25);

        JLabel wsLabel = new JLabel("WEB Service:");
        wsLabel.setBounds(50, 120, 160, 25);

        JLabel siteLabel = new JLabel("www.republicavirtual.com.br");
        siteLabel.setBounds(140, 120, 170, 25);
        siteLabel.setForeground(Color.BLUE);
        siteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        siteLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                link("https://www.republicavirtual.com.br");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        add(versionLabel);
        add(authorLabel);
        add(wsLabel);
        add(siteLabel);
    }

    private void link(String site) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(site);
            desktop.browse(uri);
        } catch (Exception e) {
            System.out.println("e");
        }
    }

}
