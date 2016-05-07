import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.motor.MediumMotor;

public class ReadingMotorPositions {

  public static void main(String[] args) {

    // Read the position value from a MediumMotor
    // Output the value only when the value changes
    // Stop after observing 10 new values
    MediumMotor motor = new MediumMotor("D");

    int count = 0;
    int lastVal = -1;
    while (count <= 50) {
      int currVal = motor.getPosition();
      if (currVal != lastVal) {
        count++;
        lastVal = currVal;
        System.out.printf("Medium motor position: %d\n", lastVal);
      }
      Delay.delayMillis(100);
    }
  }
}
