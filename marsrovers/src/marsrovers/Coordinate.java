package marsrovers;

/**
 *
 */
public class Coordinate {
  private int x;
  private int y;
  
  public Coordinate(int x,int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() { return this.x; }
  public int getY() { return this.y; }
  
  public Coordinate move(Coordinate c,Facing f) {
    return f.move(c);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (!(obj instanceof Coordinate)) return false;
    Coordinate other = (Coordinate)obj;
    if (this.x != other.x) return false;
    if (this.y != other.y) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + this.x;
    hash = 89 * hash + this.y;
    return hash;
  }
  
}
