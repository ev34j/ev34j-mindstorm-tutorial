import com.ev34j.mindstorms.motor.TankMotors;

public class SimpleTankMotors {

  public static void main(String[] args) {

    System.out.println("Trying out TankMotors");
    TankMotors motors = new TankMotors("A", "B");

    // Go forward at a 45 degree angle
    motors.onForRotations(2, 12, 25);
    motors.waitUntilStopped();

    // Go back at a 45 degree angle
    motors.onForRotations(2, -12, -25);
    motors.waitUntilStopped();
  }
}
