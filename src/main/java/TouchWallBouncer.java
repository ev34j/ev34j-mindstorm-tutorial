import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;
import com.ev34j.mindstorm.sound.Ev3Sound;

public class TouchWallBouncer {

  public static void main(final String[] args) {

    Ev3TouchSensor touch = new Ev3TouchSensor("1");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Wait for button press
    System.out.println("Press button to start");
    Ev3Sound.sayAsEnglish("Press button to start", 100);
    touch.waitUntilBumped();

    // Start motors
    motors.on(0, 25);

    // Wait for button press
    touch.waitUntilPressed();

    // Back up and stop
    motors.onForRotations(2, 0, -50);
    Ev3Sound.sayAsEnglish("I hit the wall", 100);
    motors.waitUntilStopped();
  }
}
