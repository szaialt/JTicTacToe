import java.awt.*;
import javax.swing.*;

public class TicTacToe {

  public TicTacToe(){}

  public void game() {
    //Get the size of screen, the sizes of the windows are set according to it
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int w = (int)Math.round(screenSize.getWidth());
    int h = (int)Math.round(screenSize.getHeight());
    //We accommodate the sizes to the minimal size
    int size = Math.min(w, h)/2;
    
    //The window of the game
    JFrame frame = new JFrame("TicTacToe");
    frame.setSize(size, size);

    //Make the board
    int sizeOfBoard = 3; //The size of the board
    //The gamers
    Gamer attacker = new Gamer(GamerIdentifier.ATTACKER, Symbol.X);
    Gamer defender = new Gamer(GamerIdentifier.DEFENDER, Symbol.O);
    //The surface of the game
    Surface p = new Surface(new Driver(sizeOfBoard, attacker, defender), sizeOfBoard);
    //added tho the frame of the game
    frame.add(p);
    frame.setVisible(true);
  }
}