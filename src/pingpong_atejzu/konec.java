
package pingpong_atejzu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author atejk
 */
public class konec extends JFrame {

    public konec(String winner) {
        setTitle("Konec Igre");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel label = new JLabel(winner + " je zmagal!", SwingConstants.CENTER);
        JButton restartBtn = new JButton("Ponovna Igra");
        JButton nazajBtn = new JButton("Nazaj na Login");
        JButton izhodBtn = new JButton("Izhod");

        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                okno gameWindow = new okno();
                gameWindow.setVisible(true);
            }
        });

        nazajBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                zacetnaStran zacetek = new zacetnaStran();
                zacetek.setVisible(true);
            }
        });

        izhodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLayout(new GridLayout(4, 1));
        add(label);
        add(restartBtn);
        add(nazajBtn);
        add(izhodBtn);
    }
}
