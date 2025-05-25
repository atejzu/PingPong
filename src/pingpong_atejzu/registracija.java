
package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author atejk
 */
public class registracija extends JFrame {
    private JTextField imeField;
    private JTextField priimekField;
    private JTextField uporabniskoImeField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField ponovniPasswordField;
    private JTextField telField;
    private JButton registracijaBtn;

    public registracija() {
        setTitle("Registracija");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));


        JLabel imeLabel = new JLabel("Ime:");
        imeField = new JTextField();
        JLabel priimekLabel = new JLabel("Priimek:");
        priimekField = new JTextField();
        JLabel uporabniskoImeLabel = new JLabel("Username:");
        uporabniskoImeField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Geslo:");
        passwordField = new JPasswordField();
        JLabel ponovniPasswordLabel = new JLabel("Potrdi geslo:");
        ponovniPasswordField = new JPasswordField();
        JLabel telLabel = new JLabel("Telefon:");
        telField = new JTextField();

        registracijaBtn = new JButton("Registriraj se");


        add(imeLabel);
        add(imeField);
        add(priimekLabel);
        add(priimekField);
        add(uporabniskoImeLabel);
        add(uporabniskoImeField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(ponovniPasswordLabel);
        add(ponovniPasswordField);
        add(telLabel);
        add(telField);
        add(new JLabel());
        add(registracijaBtn);


        registracijaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pridobitevProfila();
            }
        });
    }

    private void pridobitevProfila() {
        String ime = imeField.getText();
        String priimek = priimekField.getText();
        String username = uporabniskoImeField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String preverjanjePassword = new String(ponovniPasswordField.getPassword());
        String telefon = telField.getText();

        if (!password.equals(preverjanjePassword)) {
            JOptionPane.showMessageDialog(this, "Gesli se ne ujemata!", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashedPassword = zacetnaStran.hashPassword(password);
        pisanjeVProfileDatoteko(ime, priimek, username, email, hashedPassword, telefon);
    }

    private void pisanjeVProfileDatoteko(String ime, String priimek, String username, String email, String password, String telefon) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("profili.txt", true))) {
            System.out.println(new File("profili.txt").getAbsolutePath());
            writer.write(username + "," + password + "," + ime + "," + priimek + "," + email + "," + telefon);
            writer.newLine();
            writer.flush();
            JOptionPane.showMessageDialog(this, "Registracija uspešna!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Napaka pri pisanju v datoteko!", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }
}