import com.ev34j.mindstorms.motor.TankMotors;
import com.ev34j.mindstorms.time.Wait;

public class SimpleTankMotors {

  public static void main(String[] args) {

    System.out.println("Trying out TankMotors");
    TankMotors motors = new TankMotors("A", "B");

    // Go forward 2 rotations at a 45 degree angle
    motors.onForRotations(2, 12, 25);
    motors.waitUntilStopped();

    // Pause
    Wait.forSecs(1);

    // Go back 2 rotations at a 45 degree angle
    motors.onForRotations(2, -12, -25);
    motors.waitUntilStopped();
  }
}
