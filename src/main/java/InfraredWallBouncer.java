import com.ev34j.mindstorms.motor.SteeringMotors;
import com.ev34j.mindstorms.sensor.Ev3InfraredSensor;
import com.ev34j.mindstorms.sound.Ev3Sound;
import com.ev34j.mindstorms.time.Wait;

public class InfraredWallBouncer {

  public static void main(final String[] args) {

    Ev3InfraredSensor ir = new Ev3InfraredSensor("4");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Wait for a hand wave to start
    System.out.println("Pass hand in front of IR sensor to start");
    Ev3Sound.say("Pass hand in front of IR sensor to start", 100);
    while (ir.getDistancePercent() > 15) {
      Wait.forMillis(10);
    }

    // Start motors
    motors.on(0, 20);

    // Delay in case hand is still seen by the InfraredSensor
    Wait.forMillis(500);

    // Wait to get close to wall
    System.out.println("Getting close to wall before backing up");
    int lastVal = -1;
    while (true) {
      int currVal = ir.getDistancePercent();
      if (currVal < 23)
        break;

      if (currVal != lastVal) {
        lastVal = currVal;
        System.out.printf("Distance: %s\n", lastVal);
      }
      Wait.forMillis(10);
    }

    // Back up and stop
    motors.onForRotations(0, -50, 1);
    Ev3Sound.say("I got close to the wall", 100);
    motors.waitUntilStopped();
  }
}
