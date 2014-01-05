package marsrovers;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Plateau {
  private final int xlimit;
  private final int ylimit;
  // Set is better for Distinct Rovers,
  // However, it is more difficult to maintain order with a Set ...
  // thus is opted for a simple List. We can later enforce differently.
  List<Rover> rovers;
  public Plateau(int xlimit,int ylimit) {
    this.xlimit = xlimit;
    this.ylimit = ylimit;
    rovers = new LinkedList<Rover>();
  }

  public void place(Rover rover) {
    rovers.add(rover);
  }
  
  public void run() throws CollisionException, DropoffException {
    boolean moved;
    do {
      moved = false;
      for (Rover rover:rovers) {
        moved = moved || rover.step();
        rover.checkCollision(this);
        rover.checkDropoff(this);
      }
    } while (moved);
  }
  
  public void printState() {
    for (Rover rover:rovers) {
      rover.printState();
    }
  }
  
  public List getRovers() {
    return this.rovers;
  }
  
  public int getXLimit() {
    return this.xlimit;
  }
  
  public int getYLimit() {
    return this.ylimit;
  }
}
