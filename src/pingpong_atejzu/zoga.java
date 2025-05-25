package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author atejk
 */
public class zoga extends Rectangle {
    
    Random random;
    int xHitrost;
    int yHitrost;
    int hitrostzoge = 2;
    
    zoga(int x, int y, int visina, int sirina){
        super(x, y, visina, sirina);
        random = new Random();
        int randomXSmer = random.nextInt(2);
        if(randomXSmer == 0)
            randomXSmer--;
        setXsmer(randomXSmer*hitrostzoge);
        
        int randomYSmer = random.nextInt(2);
        if(randomYSmer == 0)
            randomYSmer--;
        setYsmer(randomYSmer*hitrostzoge);
    }
    
    public void setXsmer(int randomXsmer){
        xHitrost = randomXsmer;
    }
    
    public void setYsmer(int randomYsmer){
        yHitrost = randomYsmer;
    }
    
    public void premikanje(){
        x += xHitrost;
        y += yHitrost;
    }
    
    public void kreiranje(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }
    
}
