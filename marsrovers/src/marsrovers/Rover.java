package marsrovers;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Rover {
  private String name;
  private Facing facing;
  private Coordinate coordinate;
  private Queue<Command> q = new LinkedList<Command>();
  
  public Rover(String name,Facing facing,int x,int y) {
    this.name = name;
    this.facing = facing;
    this.coordinate = new Coordinate(x, y);
  }
  
  public void loadCommandQueue(String commands) {
    for (char command:commands.toCharArray()) {
      q.add(Command.valueOf(Character.toString(command)));
    }
  }
  
  public boolean step() {
    Command command = q.poll();
    if (command == null) return false;
    step(command);
    return true;
  }
  
  private void move() {
    coordinate = facing.move(coordinate);
  }
  
  private void turn(Direction direction) {
    facing = facing.turn(direction);
  }

  private void step(Command command) {
    switch (command) {
      case L: turn(Direction.L); break;
      case R: turn(Direction.R); break;
      case M: move(); break;
      default: throw new IllegalStateException("Unaccounted Command");
    }
  }
  
  public void printState() {
    System.out.println("Rover "+getName()+": "+coordinate.getX()+" "+coordinate.getY()+" "+facing);
  }
  
  public String getName() {
    return this.name;
  }
  
  void checkCollision(Plateau p) throws CollisionException {
    for (Rover other:p.rovers) {
      if (other == this) continue;
      checkCollision(other);
    }
  }

  private void checkCollision(Rover other) throws CollisionException {
    if (this.coordinate.equals(other.coordinate)) throw new CollisionException();
  }

  void checkDropoff(Plateau p) throws DropoffException {
    if (this.coordinate.getX() > p.getXLimit()) throw new DropoffException();
    if (this.coordinate.getY() > p.getYLimit()) throw new DropoffException();
    if (this.coordinate.getX() < 0) throw new DropoffException();
    if (this.coordinate.getY() < 0) throw new DropoffException();
  }
  
  public Facing getFacing() {
    return this.facing;
  }
  
  public Coordinate getCoordinate() {
    return this.coordinate;
  }
}
