package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author atejk
 */
public class loparji extends Rectangle {
    
    int id;
    int yHitrost;
    int hitrost = 10;
    
    loparji(int x, int y, int SIRINALOPARJA, int VISINALOPARJA, int id){
        super(x, y, SIRINALOPARJA, VISINALOPARJA);
        this.id = id;
        
    }
    
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYsmer(-hitrost);
                    premikanje();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYsmer(hitrost);
                    premikanje();
                }
                break;
                
             case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYsmer(-hitrost);
                    premikanje();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYsmer(hitrost);
                    premikanje();
                }
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){
     switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYsmer(0);
                    premikanje();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYsmer(0);
                    premikanje();
                }
                break;
                
             case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYsmer(0);
                    premikanje();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYsmer(0);
                    premikanje();
                }
                break;
        }
    }
    
    public void setYsmer(int ySmer){
        yHitrost = ySmer;
    }
    
    public void premikanje(){
        y = y+yHitrost;
    }
    
    public void kreiranje(Graphics g){
        if(id == 1){
            g.setColor(Color.green);
        }
        
        else{
            g.setColor(Color.pink);
        }
        g.fillRect(x, y, width, height);
    }
    
}
