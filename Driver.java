import java.util.Vector;
import javax.swing.*;
import java.awt.BorderLayout;

public class Driver {

  int sizeOfBoard; //The size of the board
  Gamer next; //The player who has his or her round
  Gamer attacker; //The beginner player who stays ahead
  Gamer defender; //The another player

  Driver(int sizeOfBoard, Gamer defender, Gamer attacker){
    this.sizeOfBoard = sizeOfBoard;
    this.next = attacker;
    this.attacker = attacker;
    this.defender = defender;
  }

  public boolean HasYouASymbolThere(Gamer gamer, int x, int y){
    //Is this field occupied by this gamer?
    for(int i=0; i< gamer.getPoints().size();i++){
      //Get the coordinates
      int x1 =  gamer.getPoints().elementAt(i).getX();
      int y1 =  gamer.getPoints().elementAt(i).getY();
      //and compare with the given ones
      if (x1 == x){
        if (y1 == y){
          return true;
        }
      }
    }
  return false;
  }

  public  boolean isMoveLegal(int a, int b){
    //Don't go out of the board
    if (a < 0) return false;
    if (b < 0) return false;
    if (a >= sizeOfBoard) return false;
    if (b >= sizeOfBoard) return false;
    //Don't require an occupied field
    if (HasYouASymbolThere(attacker, a, b)) return false;  
    if (HasYouASymbolThere(defender, a, b)) return false;    
    //Else the move is legal
    return true;
  }

  public boolean move(int a, int b){
    if (isMoveLegal(a, b)){
    //If the move is legal, the player who has his or her round 
    //can occupy the field given by the coordinates (a, b)
      Point<Integer> p = new Point<Integer>(a, b);
      next.addPoint(p);
      //move was successful
      return true;
    }
    else {
      //move failed
      return false;
    }
  }
  
  public boolean isGameOver(){
  //The board is full
    return (attacker.getPoints().size()+defender.getPoints().size() == sizeOfBoard * sizeOfBoard);
  }

  //Has the gamer the full row y occupied?
  private boolean hasYouTheRow(Gamer gamer, int y){
    //The symbols are there
    Vector<Point<Integer>> symbols = gamer.getPoints();
    //Calculates the symbols in the row y
    int num = 0;
    for (int i = 0; i < symbols.size(); i++){
      if (symbols.elementAt(i).getY() == y) num++;
    }
    //If the gamer has sizeOfBoard symblos in the row y, he or she 
    //has the full row y occupied
    if (num == sizeOfBoard) return true;
    else return false;    
  }
  
  //Has the gamer the full column x occupied?
  private boolean hasYouTheColumn(Gamer gamer, int x){
     //The symbols are there
     Vector<Point<Integer>> symbols = gamer.getPoints();
     //Calculate the symbols in the row y
    int num = 0;
    for (int i = 0; i < symbols.size(); i++){
      if (symbols.elementAt(i).getX() == x) num++;
    }
    //If the gamer has sizeOfBoard symblos in the row y, he or she 
    //has the full row y occupied
    if (num == sizeOfBoard) return true;
    else return false;
  }
  
  //Is the game won by a Gamer?
  public Gamer won(){
    for (int i = 0; i < sizeOfBoard; i++){
      if (hasYouTheColumn(attacker, i)) return attacker;
      if (hasYouTheRow(attacker, i)) return attacker;
      if (hasYouTheColumn(defender, i)) return defender;
      if (hasYouTheRow(defender, i)) return defender;
    }
    return null;
  }
  
  //The window of the outcome
   public void window(String message){
    JFrame frame = new JFrame("TicTacToe eredmény");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel(message);
    frame.getContentPane().add(label, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  } 
  
  public Gamer getNext(){
    return this.next;
  }
  
  public Gamer getAttacker(){
    return this.attacker;
  }
  
  public Gamer getDefender(){
    return this.defender;
  }
  
  //It sets the next to his or her adversary
  public void setNextToItsAdversary(){
    if (this.next == attacker) {
      this.next = defender;
    }
    else {
      this.next = attacker;
    }
  }
}