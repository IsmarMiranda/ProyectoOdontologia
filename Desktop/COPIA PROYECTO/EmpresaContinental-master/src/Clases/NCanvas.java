
package Clases;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;


public class NCanvas extends Canvas implements KeyListener,MouseListener,MouseMotionListener{
    int x=-1,y=-1,x2=-1,y2=-1; //posiciones del mouse
    boolean nnw = false;
    public NCanvas(){
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        setBackground(Color.white);
    }
    
   // @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.3f));
        g2.setColor(Color.black);
        if (x2 != -1 &&  x!= -1) {
           g2.drawLine(x, y, x2, y2);
        }
        
        if (nnw) {
            g2.setColor(Color.white);
            g2.fillRect(-10, -10, 600, 600);   
            nnw = false;
        }
        
    }
    public void dibujar(Graphics g){
          Graphics2D g2;
        Line2D line2D;
        Stroke bordeFigura;

        line2D = new Line2D.Float(x,y,x2,y2);
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(2.3f,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(Color.red);
        g2.setStroke(bordeFigura);
        g2.draw(line2D);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar()=='n') {
            nnw = true;
            print(this.getGraphics());
        }
 
    }

    @Override
    public void keyPressed(KeyEvent ke) {
      
    }

    @Override
    public void keyReleased(KeyEvent ke) {
     
    }

    @Override
    public void mouseClicked(MouseEvent me) {
     
    }

    @Override
    public void mousePressed(MouseEvent me) {
     x=me.getX();
     y=me.getY();
        //dibujar(this.getGraphics());
      print(this.getGraphics());
     // repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       x = -1;
       x2 = -1;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
     
    }

    @Override
    public void mouseExited(MouseEvent me) {
     
    }

    @Override
    public void mouseDragged(MouseEvent me) {
     x2 = me.getX();
     y2= me.getY();
        print(this.getGraphics());
        x=x2;
        y=y2;
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
                    
    }
    public void setX(int a){
        x =a;
    }
    public void setY(int a){
        y =a;
    }
}
