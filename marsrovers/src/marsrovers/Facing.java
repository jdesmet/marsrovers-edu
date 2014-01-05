package marsrovers;

/**
 *
 */
public enum Facing {
  N(1, 0, 1),
  S(3, 0,-1),
  E(0, 1, 0),
  W(2,-1, 0);
  // Movement Vector:
  private final int x;
  private final int y;
  private final int angle; // CCW Positive
  // Unfortunatly, cant make it final
  private Facing left;
  private Facing right;
  
  private static final Facing[] INDEX_BY_ANGLE;
  
  static {
    INDEX_BY_ANGLE = new Facing[4];
    for (Facing f:values()) {
      INDEX_BY_ANGLE[f.angle] = f;
    }
    for (Facing f:INDEX_BY_ANGLE) {
      int leftAngle = (f.angle + 1) % 4;
      int rightAngle = (f.angle + 3) % 4;
      // This is the reason why it left/right can't be final
      f.left = INDEX_BY_ANGLE[leftAngle];
      f.right = INDEX_BY_ANGLE[rightAngle];
    }
  }
  
  private Facing(int angle,int x,int y) {
    this.x = x;
    this.y = y;
    this.angle = angle;
  }
  
  public Coordinate move(Coordinate c) {
    return new Coordinate(c.getX()+x,c.getY()+y);
  }

  Facing turn(Direction direction) {
    switch (direction) {
      case L: return left;
      case R: return right;
      default: throw new IllegalStateException("Unnaccounted Directoin");
    }
  }
}
