import java.util.Vector;

public class Gamer {
  
  GamerIdentifier id; // Indentificator of the gamer
  Symbol symbol; //For the Surface class to draw
  Vector<Point<Integer>> points; //The fields of the player
  
  public Gamer(GamerIdentifier id, Symbol symbol){
    this.id = id;
    this.symbol = symbol;
    this.points = new Vector<Point<Integer>>();
  }
  
  public GamerIdentifier getId(){
    return this.id;
  }

  public void setId(GamerIdentifier id){
    this.id = id;;
  }

  public Symbol getSymbol(){
    return this.symbol;
  }
  
  public void setSymbol(Symbol s){
    this.symbol = s;
  }

  public Vector<Point<Integer>> getPoints(){
    return this.points;
  }
  
  public void addPoint(Point<Integer> p){
    this.points.add(p);
  }
  
}