import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;

public class TouchWallBouncer {

  public static void main(final String[] args) {

    Ev3TouchSensor touch = new Ev3TouchSensor("1");
    SteeringMotors steering = new SteeringMotors("A", "B");

    // Wait for button press
    System.out.println("Press button to start");
    touch.waitUntilBumped();

    // Start motors
    steering.on(0, 25);

    // Wait for button press
    System.out.println("Run into wall stop");
    touch.waitUntilPressed();

    // Back up and stop
    System.out.println("Backing up");
    steering.onForRotations(1, 0, -50);
    steering.waitUntilStopped();
  }
}
