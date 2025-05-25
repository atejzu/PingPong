package pingpong_atejzu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 *
 * @author atejk
 */
public class igra extends JPanel implements Runnable {
    
    static final int SIRINAIGRE = 1000;
    static final int VISINAIGRE = (int)(SIRINAIGRE*(0.5555));
    static final Dimension VELIKOSTZASLONA = new Dimension(SIRINAIGRE,VISINAIGRE);
    static final int VELIKOSTZOGE = 20;
    static final int SIRINALOPARJA = 25;
    static final int VISINALOPARJA = 100;
    static final int DOLZINAIMENA = (zacetnaStran.ime1.length()) * 40;
    static final int DOLZINAIMENA2 = (zacetnaStran.ime1.length()-1) * 40;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    loparji lopar1;
    loparji lopar2;
    zoga zoga;
    score score;
    private boolean running = true;
    
    
    igra(){
        noviLoparji();
        novaZoga();
        score = new score(SIRINAIGRE, VISINAIGRE);
        this.setFocusable(true);
        this.addKeyListener(new al());
        this.setPreferredSize(VELIKOSTZASLONA);
        this.setBackground(Color.black);
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void novaZoga(){
        random = new Random();
        zoga = new zoga((SIRINAIGRE/2)-(VELIKOSTZOGE/2), random.nextInt(VISINAIGRE-VELIKOSTZOGE), VELIKOSTZOGE, VELIKOSTZOGE);
    }
    
    public void noviLoparji(){
        lopar1 = new loparji(0,  (VISINAIGRE/2) - (VISINALOPARJA/2), SIRINALOPARJA, VISINALOPARJA, 1);
        lopar2 = new loparji(SIRINAIGRE-SIRINALOPARJA,  (VISINAIGRE/2) - (VISINALOPARJA/2), SIRINALOPARJA, VISINALOPARJA, 2);
    }
    
    @Override
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        kreiranje(graphics);
        g.drawImage(image, 0, 0, this);
    }
    
    public void kreiranje(Graphics g){
        lopar1.kreiranje(g);
        lopar2.kreiranje(g);
        zoga.kreiranje(g);
        score.kreiranje(g);
    }
    
    public void premikanje(){
        lopar1.premikanje();
        lopar2.premikanje();
        zoga.premikanje();
    }
    
    public void robovi(){
        if(zoga.y <= 0){
            zoga.setYsmer(-zoga.yHitrost);
        }
        
        if(zoga.y >= VISINAIGRE-VELIKOSTZOGE){
            zoga.setYsmer(-zoga.yHitrost);
        }
        
        if(zoga.intersects(lopar1)){
            zoga.xHitrost = Math.abs(zoga.xHitrost);
            zoga.xHitrost++;
            if(zoga.yHitrost > 0)
                zoga.yHitrost++;
            else
                zoga.yHitrost--;
            zoga.setXsmer(zoga.xHitrost);
            zoga.setYsmer(zoga.yHitrost);
        }
        
         if(zoga.intersects(lopar2)){
            zoga.xHitrost = Math.abs(zoga.xHitrost);
            zoga.xHitrost++;
            if(zoga.yHitrost > 0)
                zoga.yHitrost++;
            else
                zoga.yHitrost--;
            zoga.setXsmer(-zoga.xHitrost);
            zoga.setYsmer(zoga.yHitrost);
        }
        
        if(lopar1.y <= 0)
            lopar1.y = 0;
        if(lopar1.y >= (VISINAIGRE-VISINALOPARJA))
           lopar1.y =  VISINAIGRE-VISINALOPARJA;
        if(lopar2.y <= 0)
            lopar2.y = 0;
        if(lopar2.y >= (VISINAIGRE-VISINALOPARJA))
           lopar2.y =  VISINAIGRE-VISINALOPARJA;
        
        if(zoga.x<=0){
                score.igralec2++;
                noviLoparji();
                novaZoga();
                if(score.igralec2 == 3){
                    score.igralec2Runde++;
                    score.igralec1 = 0;
                    score.igralec2 = 0;
                }
            }
            
            if(zoga.x >= SIRINAIGRE-VELIKOSTZOGE){
                score.igralec1++;
                noviLoparji();
                novaZoga();
                if(score.igralec1 == 3){
                    score.igralec1Runde++;
                    score.igralec1 = 0;
                    score.igralec2 = 0;
                }
            }
            
        if(score.igralec1Runde == 3)
            konecIgre(zacetnaStran.ime1);
        if(score.igralec2Runde == 3)
            konecIgre(zacetnaStran.ime2);
    }
    
    private void konecIgre(String zmagovalec) {
        running = false;
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.dispose();
        }
        SwingUtilities.invokeLater(() -> new konec(zmagovalec).setVisible(true));
    }
    
    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTics = 60.0;
        double ns = 1000000000/amountOfTics;
        double delta = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                premikanje();
                robovi();
                repaint();
                delta--;
            }
        }
    }
    
    public class al extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            lopar1.keyPressed(e);
            lopar2.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            lopar1.keyReleased(e);
            lopar2.keyReleased(e);
        }
    }
    
}
