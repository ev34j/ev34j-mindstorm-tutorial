import com.ev34j.mindstorms.motor.SteeringMotors;
import com.ev34j.mindstorms.sensor.Ev3TouchSensor;
import com.ev34j.mindstorms.sound.Ev3Sound;

public class TouchWallBouncer {

  public static void main(final String[] args) {

    // Declare sensor and motors
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
    motors.onForRotations(0, -50, 2);
    Ev3Sound.say("I hit the wall", 100);
    motors.waitUntilStopped();
  }
}
