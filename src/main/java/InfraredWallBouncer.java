import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sensor.Ev3InfraredSensor;

public class InfraredWallBouncer {

  public static void main(final String[] args) {

    Ev3InfraredSensor ir = new Ev3InfraredSensor("4");
    SteeringMotors sm = new SteeringMotors("A", "B");

    // Wait for button press
    System.out.println("Pass hand in front of IR sensor to start");
    while (ir.getDistancePercent() > 10) {
      Delay.delayMillis(10);
    }

    // Start motors
    sm.on(0, 15);

    // Delay in case hand is still seen by the IRSensor
    Delay.delayMillis(500);

    // Wait to get close to wall
    System.out.println("Get close to wall before backuping up");
    int lastVal = -1;
    while (true) {
      int currVal = ir.getDistancePercent();
      if (currVal < 20)
        break;

      if (currVal != lastVal) {
        lastVal = currVal;
        System.out.printf("Distance: %s\n", lastVal);
      }
      Delay.delayMillis(10);
    }

    // Back up and stop
    sm.onForRotations(1, 0, -50);
    sm.waitUntilStopped();
  }
}
