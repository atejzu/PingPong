

package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author atejk
 */
public class urejanjeProfila extends JFrame {
    private JTextField uporabniskoImeField;
    private JPasswordField passwordField;
    private JTextField imeField;
    private JTextField priimekField;
    private JTextField emailField;
    private JTextField telefonField;
    private JTextField novoUporabniskoImeField;
    private JPasswordField novoGesloField;
    private JButton potrdiBtn;

    public urejanjeProfila() {
        setTitle("Urejanje profila");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2));


        JLabel usernameLabel = new JLabel("Trenutno uporabniško ime:");
        uporabniskoImeField = new JTextField();
        JLabel passwordLabel = new JLabel("Trenutno geslo:");
        passwordField = new JPasswordField();
        JLabel imeLabel = new JLabel("Ime:");
        imeField = new JTextField();
        JLabel priimekLabel = new JLabel("Priimek:");
        priimekField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel telefonLabel = new JLabel("Telefon:");
        telefonField = new JTextField();
        JLabel novoUporabniskoImeLabel = new JLabel("UporabniskoIme:");
        novoUporabniskoImeField = new JTextField();
        JLabel novoGesloLabel = new JLabel("Novo geslo:");
        novoGesloField = new JPasswordField();
        potrdiBtn = new JButton("Potrdi");


        add(usernameLabel);
        add(uporabniskoImeField);
        add(passwordLabel);
        add(passwordField);
        add(imeLabel);
        add(imeField);
        add(priimekLabel);
        add(priimekField);
        add(emailLabel);
        add(emailField);
        add(telefonLabel);
        add(telefonField);
        add(novoUporabniskoImeLabel);
        add( novoUporabniskoImeField);
        add(novoGesloLabel);
        add(novoGesloField);
        add(new JLabel());
        add(potrdiBtn);


        potrdiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urediProfil();
            }
        });
    }

    private void urediProfil() {
        String trenutnoUporabniskoIme = uporabniskoImeField.getText();
        String trenutnoGeslo = new String(passwordField.getPassword());
        String ime = imeField.getText();
        String priimek = priimekField.getText();
        String email = emailField.getText();
        String telefon = telefonField.getText();
        String novoUporabniskoIme = novoUporabniskoImeField.getText();
        String novoGeslo = new String(novoGesloField.getPassword());


        if (!preveriUporabnika(trenutnoUporabniskoIme, trenutnoGeslo)) {
            JOptionPane.showMessageDialog(this, "Napačno uporabniško ime ali geslo!", "Napaka", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (!veljavnostPodatkov(ime, priimek, email, telefon, novoUporabniskoIme, novoGeslo)) {
            return;
        }


        posodobiProfil(trenutnoUporabniskoIme, ime, priimek, email, telefon, novoUporabniskoIme, novoGeslo);

        JOptionPane.showMessageDialog(this, "Profil uspešno posodobljen!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
        if (zacetnaStran.instance != null) {
                        zacetnaStran.instance.resetLogin();
                    }
        dispose();
    }

    private boolean preveriUporabnika(String username, String password) {
        String hashedPassword = zacetnaStran.hashPassword(password);
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

    private boolean veljavnostPodatkov(String ime, String priimek, String email, String telefon, String novoUporabniskoIme, String novoGeslo) {

        return true;
    }

    private void posodobiProfil(String trenutnoUporabniskoIme, String ime, String priimek, String email, String telefon, String novoUporabniskoIme, String novoGeslo) {
        String hashedPassword = zacetnaStran.hashPassword(novoGeslo);
        try {
            File inputFile = new File("profili.txt");
            File tempFile = new File("profili_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(trenutnoUporabniskoIme)) {
                    writer.write(novoUporabniskoIme + "," + hashedPassword + "," + ime + "," + priimek + "," + email + "," + telefon);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            inputFile
                    .delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Napaka pri posodabljanju profila!", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                urejanjeProfila frame = new urejanjeProfila();
                frame.setVisible(true);
            }
        });
    }
}

