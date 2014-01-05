package marsrovers;

/**
 *
 */
public class Marsrovers {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Plateau p = new Plateau(5,5);
    Rover rover1 = new Rover("Rover 1",Facing.N,1,2);
    Rover rover2 = new Rover("Rover 2",Facing.E,3,3);
    rover1.loadCommandQueue("LMLMLMLMM");
    rover2.loadCommandQueue("MMRMMRMRRM");
    p.place(rover1);
    p.place(rover2);
    try {
      p.run();
    } catch (CollisionException ex) {
      System.err.println("Collission Detected!");
    } catch (DropoffException ex) {
      System.err.println("Dropoff Detected!");
    }
    p.printState();
  }
}
