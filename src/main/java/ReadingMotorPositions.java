import com.ev34j.mindstorm.motor.MediumMotor;
import com.ev34j.mindstorm.time.Wait;

public class ReadingMotorPositions {

  public static void main(String[] args) {

    // Read the position value from a MediumMotor
    // Output the value only when the value changes
    // Stop after observing 50 new values
    MediumMotor motor = new MediumMotor("D");

    int count = 0;
    int lastVal = -1;
    while (count <= 50) {
      int currVal = motor.getPosition();
      // Do not print out the position if it is unchanged
      if (currVal != lastVal) {
        count++;
        lastVal = currVal;
        System.out.printf("Medium motor position: %d\n", lastVal);
      }
      Wait.forMillis(100);
    }
  }
}
