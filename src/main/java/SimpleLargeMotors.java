import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.LargeMotor;

public class SimpleLargeMotors {

  public static void main(String[] args) {

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
  }
}
