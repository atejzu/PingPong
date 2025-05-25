
package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author atejk
 */
public class zacetnaStran extends JFrame {
    public static zacetnaStran instance;
    private JTextField username1Polje;
    private JPasswordField geslo1Polje;
    private JTextField username2Polje;
    private JPasswordField geslo2Polje;
    private JButton igralec1LoginBtn;
    private JButton igralec2LoginBtn;
    private JButton zacetekBtn;
    private JButton registracijaBtn;
    private JButton urejanjeProfilaBtn;
    public static String ime1, ime2;

    public zacetnaStran() {
        instance = this;

        this.setTitle("Začetni zaslon");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(8, 2)); 

        
        JLabel username1Label = new JLabel("Igralec 1 Username:");
        username1Polje = new JTextField();
        JLabel password1Label = new JLabel("Igralec 1 Password:");
        geslo1Polje = new JPasswordField();
        igralec1LoginBtn = new JButton("Prijava Igralec 1");

        JLabel username2Label = new JLabel("Igralec 2 Username:");
        username2Polje = new JTextField();
        JLabel password2Label = new JLabel("Igralec 2 Password:");
        geslo2Polje = new JPasswordField();
        igralec2LoginBtn = new JButton("Prijava Player 2");

        
        zacetekBtn = new JButton("Začni igro");
        zacetekBtn.setEnabled(false);

        
        registracijaBtn = new JButton("Registracija");
        registracijaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registracija registracijaOkno = new registracija();
                registracijaOkno.setVisible(true);
            }
        });


        urejanjeProfilaBtn = new JButton("Uredi profil");
        urejanjeProfilaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urejanjeProfila profilOkno = new urejanjeProfila();
                profilOkno.setVisible(true);
            }
        });


        igralec1LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (preveriLogin(username1Polje.getText(), new String(geslo1Polje.getPassword()))) {
                    igralec1LoginBtn.setEnabled(false);
                    ime1 = username1Polje.getText();
                    obaIgralcaPrijvljena();
                } else {
                    JOptionPane.showMessageDialog(zacetnaStran.this, "Neveljavna prijava za Player 1!", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        igralec2LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (preveriLogin(username2Polje.getText(), new String(geslo2Polje.getPassword()))) {
                    igralec2LoginBtn.setEnabled(false);
                    ime2 = username2Polje.getText();
                    obaIgralcaPrijvljena();
                } else {
                    JOptionPane.showMessageDialog(zacetnaStran.this, "Neveljavna prijava za Player 2!", "Napaka", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        zacetekBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okno gameWindow = new okno();
                gameWindow.setVisible(true);
                dispose();
            }
        });


        this.add(username1Label);
        this.add(username1Polje);
        this.add(password1Label);
        this.add(geslo1Polje);
        this.add(igralec1LoginBtn);
        this.add(new JLabel());
        this.add(username2Label);
        this.add(username2Polje);
        this.add(password2Label);
        this.add(geslo2Polje);
        this.add(igralec2LoginBtn);
        this.add(new JLabel());
        this.add(registracijaBtn);
        this.add(urejanjeProfilaBtn);
        this.add(zacetekBtn);
    }


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    private void obaIgralcaPrijvljena() {
        if (!igralec1LoginBtn.isEnabled() && !igralec2LoginBtn.isEnabled()) {
            zacetekBtn.setEnabled(true);
        }
    }
    
    public void resetLogin() {
        igralec1LoginBtn.setEnabled(true);
        igralec2LoginBtn.setEnabled(true);
        zacetekBtn.setEnabled(false);
        ime1 = null;
        ime2 = null;
        username1Polje.setText("");
        geslo1Polje.setText("");
        username2Polje.setText("");
        geslo2Polje.setText("");
    }


    private boolean preveriLogin(String username, String password) {
        String hashedPassword = hashPassword(password);
        try (BufferedReader reader = new BufferedReader(new FileReader("profili.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(hashedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Napaka pri branju datoteke!", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                zacetnaStran frame = new zacetnaStran();
                frame.setVisible(true);
            }
        });
    }
}
