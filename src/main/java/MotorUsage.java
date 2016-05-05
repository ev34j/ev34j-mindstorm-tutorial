import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.LargeMotor;
import com.ev34j.mindstorm.motor.SteeringMotors;

public class MotorUsage {

  public static void main(final String[] args) {

    // Run the motors as separate objects
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


    System.out.println("Trying out SteeringMotors");
    final SteeringMotors sm = new SteeringMotors("A", "B");
    sm.onForRotations(2, 20, 25);
    sm.waitUntilStopped();
    sm.onForRotations(2, 20, -25);
    sm.waitUntilStopped();

  }
}
