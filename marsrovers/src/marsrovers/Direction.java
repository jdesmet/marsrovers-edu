package marsrovers;

/**
 *
 */
public enum Direction {
  L(1), R(-1);
  private int angle;

  private Direction(int angle) {
    this.angle = angle;
  }
  
  int getAngle() {
    return this.angle;
  }
}
