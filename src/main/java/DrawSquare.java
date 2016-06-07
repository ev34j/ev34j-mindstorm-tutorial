import com.ev34j.mindstorms.motor.TankMotors;

public class DrawSquare {

  public static void main(String[] args) {

    System.out.println("Drawing a Square");
    TankMotors tank = new TankMotors("A", "B");

    for (int i = 0; i < 4; i++) {
      // Go forward 2 rotations
      tank.onForRotations(25, 25, 2);
      tank.waitUntilStopped();

      // Turn 90 degrees
      tank.onForDegrees(25, -25, 260);
      tank.waitUntilStopped();
    }
  }

}
