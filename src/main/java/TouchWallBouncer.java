import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;
import com.ev34j.mindstorm.sound.Ev3Sound;

public class TouchWallBouncer {

  public static void main(final String[] args) {

    Ev3TouchSensor touch = new Ev3TouchSensor("1");
    SteeringMotors steering = new SteeringMotors("A", "B");

    // Wait for button press
    System.out.println("Press button to start");
    Ev3Sound.sayAsEnglish("Press button to start");
    touch.waitUntilBumped();

    // Start motors
    steering.on(0, 25);

    // Wait for button press
    touch.waitUntilPressed();

    // Back up and stop
    steering.onForRotations(2, 0, -50);
    Ev3Sound.sayAsEnglish("I hit the wall");
    steering.waitUntilStopped();
  }
}
