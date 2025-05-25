package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author atejk
 */
public class score extends Rectangle{
    
    static int SIRINAIGRE;
    static int VISINAIGRE;
    int igralec1;
    int igralec2;
    int igralec1Runde;
    int igralec2Runde;
    
    score(int SIRINAIGRE, int VISINAIGRE){
        score.SIRINAIGRE = SIRINAIGRE;
        score.VISINAIGRE = VISINAIGRE;
    }
    
    public void kreiranje(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(SIRINAIGRE/2, 0, SIRINAIGRE/2, VISINAIGRE);
        g.drawString(String.valueOf(igralec1), (SIRINAIGRE/2)-80, 50);
        g.drawString(String.valueOf(igralec2), (SIRINAIGRE/2)+50, 50);
        g.setFont(new Font("Consolas", Font.PLAIN, 40));
        g.drawString(String.valueOf(zacetnaStran.ime2), (SIRINAIGRE/2)+30, 555-40);
        if(zacetnaStran.ime1.length() > 3)
            g.drawString(String.valueOf(zacetnaStran.ime1), (SIRINAIGRE/2)-igra.DOLZINAIMENA2, 555-40);
        else
            g.drawString(String.valueOf(zacetnaStran.ime1), (SIRINAIGRE/2)-igra.DOLZINAIMENA, 555-40);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString(String.valueOf(igralec2Runde), (SIRINAIGRE/2)+30, 555-10);
        g.drawString(String.valueOf(igralec1Runde), (SIRINAIGRE/2)-40, 555-10);
        
    }
    
}
