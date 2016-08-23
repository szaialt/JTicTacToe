//A generic two dimensional point class

class Point<T> {
  T x; //coordinate
  T y; //coordinate

  public Point(){}

  public Point(T x, T y){
    this.x = x;
    this.y = y;
  }

  public T getX(){
    return this.x;
  }

  public T getY(){
    return this.y;
  }

  public void setX(T x){
    this.x = x;
  }

  public void setY(T y){
    this.y = y;
  }
  
  @Override
  public int hashCode() { return x.hashCode() ^ y.hashCode(); }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point<?>)) return false;
    Point<?> pointo = (Point<?>) o;
    return this.x.equals(pointo.getX()) &&
           this.y.equals(pointo.getY());
  }

  
}