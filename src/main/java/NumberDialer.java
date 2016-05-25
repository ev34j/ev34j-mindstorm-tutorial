import com.ev34j.mindstorm.display.Ev3Display;
import com.ev34j.mindstorm.motor.MediumMotor;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;

public class NumberDialer {
  public static void main(String[] args) {

    MediumMotor motor = new MediumMotor("A");
    motor.reset();

    int lastVal = motor.getPosition();

    while (lastVal > -360 && lastVal < 360) {
      int newVal = motor.getPosition();
      if (newVal == lastVal) {
        Wait.forMillis(200);
      }
      else {
        Ev3Sound.say("Going " + (newVal < lastVal ? "down" : "up"), 100);
        Ev3Display.drawString(true, "" + newVal, 10, 10, 12, true);
        System.out.println("New val = " + newVal);
        lastVal = newVal;
      }
    }

    System.out.println("Exiting");
  }
}
