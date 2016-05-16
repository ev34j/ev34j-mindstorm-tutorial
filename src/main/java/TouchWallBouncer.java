import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;
import com.ev34j.mindstorm.sound.Ev3Sound;

public class TouchWallBouncer {

  public static void main(final String[] args) {

    Ev3TouchSensor touch = new Ev3TouchSensor("1");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Wait for button press
    System.out.println("Press touch sensor to start");
    Ev3Sound.say("Press touch sensor to start", 100);
    touch.waitUntilBumped();

    // Start motors
    motors.on(0, 25);

    // Wait for touch sensor press
    touch.waitUntilPressed();

    // Back up and stop
    motors.onForRotations(2, 0, -50);
    Ev3Sound.say("I hit the wall", 100);
    motors.waitUntilStopped();
  }
}
