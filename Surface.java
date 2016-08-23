import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
 
//import java.io.IOException;
//import java.net.URL;
//import javax.imageio.ImageIO;
import java.util.Vector;

public class Surface extends JPanel {
   private final int sizeOfBoard;  //The size of the board
   private Driver driver; //The bottom level of the game
   private int frameWidth; //Width of the top and left frame
   private int step; //Size of sides of fields
   private int mx; //Correction for drawing
   private Graphics2D g2; //The drawing Graphics class
   
  //The surface and top tear of the game
  Surface(Driver driver, int sizeOfBoard){
     this.setFocusable(true);
     this.sizeOfBoard = sizeOfBoard;
     this.driver = driver;
     //Gets the size of screen, the sizes of the windows are set according to it
     Dimension bounds = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
     int w = (int)Math.round(bounds.getWidth());
     int h = (int)Math.round(bounds.getHeight());
     //We accommodate the sizes to the minimal size
     int size = Math.min(w, h)/2;
     frameWidth = 50;

     //Calculates the size of sides of fields
     step = (size - frameWidth) / (sizeOfBoard + 1);
     //This is the correction
     mx = step * (sizeOfBoard - 1) + frameWidth;
     
     //Listener to register the mouse events
     MouseMotionHandler mmh = new MouseMotionHandler(frameWidth, step);
     addMouseListener(mmh);
     addMouseMotionListener(mmh);

   }
   
  @Override
  public void paint(Graphics g) { 
    //Sets the g2
    g2 = (Graphics2D)g;

    //The gamers
    Gamer attacker = driver.getAttacker();
    Gamer defender = driver.getDefender();
    //The fields of the gamers
    Vector<Point<Integer>> attPoints = attacker.getPoints();
    Vector<Point<Integer>> defPoints = defender.getPoints();
    //Drawing
    //Background
    g2.setColor(Color.white);
    g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    //Grid
    g2.setColor(Color.black);
    for (int i = 0; i <= sizeOfBoard + 1; i++){
      //Verticals
      g2.drawLine(frameWidth + i*step, frameWidth, frameWidth + i*step, 
         mx + step);
      //Horizontals
      g2.drawLine(frameWidth, frameWidth + i*step, 
         mx + step, frameWidth + i*step);
    }

    //Fields of the gamers
    //Of the attacker ones
    for (int i = 0; i < attPoints.size(); i++){
    Point<Integer> p = attPoints.elementAt(i);
      if (attacker.getSymbol() == Symbol.X){ 
        drawX(p.getX(), p.getY());
      }
      else if (attacker.getSymbol() == Symbol.O){ 
        drawO(p.getX(), p.getY());
      }
    }
    //Of the defender ones
    for (int i = 0; i < defPoints.size(); i++){
    Point<Integer> p = defPoints.elementAt(i);
    if (defender.getSymbol() == Symbol.X){ 
        drawX(p.getX(), p.getY());
      }
      else if (defender.getSymbol() == Symbol.O){ 
        drawO(p.getX(), p.getY());
      }
    }

  }
  //How to draw the symbol X  
  void drawX(int x, int y){
    g2.setColor(Color.red);
    g2.fillRect(frameWidth + x * step, 
    frameWidth + y * step, step, step);
  }
   //How to draw the symbol O 
  void drawO(int x, int y){
    g2.setColor(Color.blue);
    g2.fillRect(frameWidth + x * step, 
    frameWidth + y * step, step, step);
  }

  //The listener class for the mouse
    class MouseMotionHandler 
         implements MouseListener, MouseMotionListener {
  
    private int frameWidth;
    private int step;
    
    //private boolean movedAttacker;
        
    MouseMotionHandler(int frameWidth, int step){
      this.frameWidth = frameWidth;
      this.step = step;
    }
    
    public void mousePressed(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e){
      //The coordinates of the mouse click
      int x1 = e.getX();
      int y1 = e.getY();
      //calculated into the coordinates of fields
      int x = (x1 - frameWidth) / step;
      int y = (y1 - frameWidth) / step;
      //Was the move succesfull?
      boolean moved = driver.move(x, y);
      if (moved){
        //The next round is of the another gamer
        driver.setNextToItsAdversary();
        repaint();
      }
      /*
      if (driver.isGameOver()){
        Gamer attacker = driver.getAttacker();
        Gamer defender = driver.getDefender();
        Gamer winner = driver.won();
        String message = "";
        if (winner == attacker){
          message = "A kezdő nyert.";
        }
        else if (winner == defender){
          message = "A második játékos nyert.";
        }
        else if (winner.equals(null)){
          message = "Döntetlen.";
        }
        driver.window(message);
      }*/
    }
    
    public void mouseReleased(MouseEvent e){}
    
    public void mouseEntered(MouseEvent e){}
    
    public void mouseExited(MouseEvent e){}
    
    public void mouseMoved(MouseEvent e){}
    
    public void mouseDragged(MouseEvent e){}
    
  }
  
}