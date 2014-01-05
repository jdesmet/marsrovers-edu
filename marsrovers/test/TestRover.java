import marsrovers.CollisionException;
import marsrovers.Coordinate;
import marsrovers.DropoffException;
import marsrovers.Facing;
import marsrovers.Plateau;
import marsrovers.Rover;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestRover {
  
  public TestRover() {
  }
  
  @Test(expected = DropoffException.class)
  public void dropOffEast() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover = new Rover("Rover",Facing.E,5,5);
    rover.loadCommandQueue("M");
    p.place(rover);
    p.run();
  }

  @Test(expected = DropoffException.class)
  public void dropOffWest() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover = new Rover("Rover",Facing.W,0,0);
    rover.loadCommandQueue("M");
    p.place(rover);
    p.run();
  }

  @Test(expected = DropoffException.class)
  public void dropOffNorth() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover = new Rover("Rover",Facing.N,5,5);
    rover.loadCommandQueue("M");
    p.place(rover);
    p.run();
  }

  @Test(expected = DropoffException.class)
  public void dropOffSouth() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover = new Rover("Rover",Facing.S,0,0);
    rover.loadCommandQueue("M");
    p.place(rover);
    p.run();
  }
  
  @Test(expected = CollisionException.class)
  public void collide() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover1 = new Rover("Rover 1",Facing.E,0,0);
    Rover rover2 = new Rover("Rover 2",Facing.E,1,0);
    rover1.loadCommandQueue("M");
    rover2.loadCommandQueue("M");
    p.place(rover1);
    p.place(rover2);
    p.run();
  }
  
  @Test
  public void avoidCollision() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover1 = new Rover("Rover 1",Facing.E,0,0);
    Rover rover2 = new Rover("Rover 2",Facing.E,1,0);
    rover1.loadCommandQueue("M");
    rover2.loadCommandQueue("M");
    p.place(rover2); // Note that the order is switched.
    p.place(rover1);
    p.run();
  }
  
  @Test
  public void example() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover1 = new Rover("Rover 1",Facing.N,1,2);
    Rover rover2 = new Rover("Rover 2",Facing.E,3,3);
    rover1.loadCommandQueue("LMLMLMLMM");
    rover2.loadCommandQueue("MMRMMRMRRM");
    p.place(rover1);
    p.place(rover2);
    p.run();
    assertEquals(rover1.getCoordinate(), new Coordinate(1,3));
    assertEquals(rover1.getFacing(),Facing.N);
    assertEquals(rover2.getCoordinate(), new Coordinate(5,1));
    assertEquals(rover2.getFacing(),Facing.E);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void invalidInput() throws CollisionException, DropoffException {
    Plateau p = new Plateau(5,5);
    Rover rover = new Rover("Rover 1",Facing.E,0,0);
    rover.loadCommandQueue("U");
    p.place(rover);
    p.run();
  }
  
}
