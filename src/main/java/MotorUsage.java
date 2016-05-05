import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.LargeMotor;
import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.motor.TankMotors;

public class MotorUsage {

  public static void main(final String[] args) {

    // Run the motors as LargeMotor objects
    System.out.println("Trying out LargeMotors");
    LargeMotor lmA = new LargeMotor("A");
    LargeMotor lmB = new LargeMotor("B");

    // Go forward for 1 second and then turn off motors
    lmA.on(25);
    lmB.on(25);
    Delay.delaySecs(1);
    lmA.off();
    lmB.off();

    // Go back for 1 rotation
    lmA.onForRotations(1, -25);
    lmB.onForRotations(1, -25);
    lmA.waitUntilStopped();
    lmB.waitUntilStopped();

    // Run the motors as a SteeringMotors object
    System.out.println("Trying out SteeringMotors");
    final SteeringMotors sm = new SteeringMotors("A", "B");

    // Go forward at a 45 degree angle
    sm.onForRotations(2, 50, 25);
    sm.waitUntilStopped();

    // Go back at a 45 degree angle
    sm.onForRotations(2, 50, -25);
    sm.waitUntilStopped();

    // Run the motors as a TankMotors object
    System.out.println("Trying out TankMotors");
    final TankMotors tm = new TankMotors("A", "B");

    // Go forward at a 45 degree angle
    tm.onForRotations(2, 12, 25);
    tm.waitUntilStopped();

    // Go back at a 45 degree angle
    tm.onForRotations(2, -12, -25);
    tm.waitUntilStopped();
  }
}
