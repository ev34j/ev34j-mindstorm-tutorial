import com.ev34j.mindstorms.motor.LargeMotor;
import com.ev34j.mindstorms.time.Wait;

public class SimpleLargeMotors {

  public static void main(String[] args) {

    System.out.println("Trying out LargeMotors");
    LargeMotor lmA = new LargeMotor("A");
    LargeMotor lmB = new LargeMotor("B");

    // Go forward for 1 second and then turn off motors
    lmA.on(25);
    lmB.on(25);

    Wait.forSecs(1);

    lmA.off();
    lmB.off();

    // Go back for 1 rotation
    lmA.onForRotations(1, -25);
    lmB.onForRotations(1, -25);

    lmA.waitUntilStopped();
    lmB.waitUntilStopped();
  }
}
