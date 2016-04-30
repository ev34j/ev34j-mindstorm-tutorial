import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.LargeMotor;

public class MyEv3Robot {

  public static void main(final String[] args) {

    final LargeMotor lmA = new LargeMotor("A");
    final LargeMotor lmB = new LargeMotor("B");

    lmA.onForRotations(3, 50);
    lmB.onForRotations(3, 50);

    Delay.delaySecs(5);

  }
}
