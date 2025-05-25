package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author atejk
 */
public class okno extends JFrame {
    
    igra igra;
    
    okno(){
        igra = new igra();
        this.add(igra);
        this.setTitle("PinPong");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
