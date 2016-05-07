import com.ev34j.mindstorm.motor.SteeringMotors;

public class SimpleSteeringMotors {

  public static void main(String[] args) {

    // Run the motors as a SteeringMotors object
    System.out.println("Trying out SteeringMotors");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Go forward at a 45 degree angle
    motors.onForRotations(2, 50, 25);
    motors.waitUntilStopped();

    // Go back at a 45 degree angle
    motors.onForRotations(2, 50, -25);
    motors.waitUntilStopped();
  }
}
