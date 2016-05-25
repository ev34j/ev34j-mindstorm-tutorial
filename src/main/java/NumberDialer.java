import com.ev34j.mindstorm.display.Ev3Display;
import com.ev34j.mindstorm.motor.MediumMotor;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.time.Wait;

public class NumberDialer {
  public static void main(String[] args) {

    MediumMotor motor = new MediumMotor("A");
    motor.reset();

    System.out.println("Ready");
    Ev3Display.drawString(true, "Ready", 40, 60, 30, true);
    Ev3Sound.say("Ready", 100);

    int lastVal = motor.getPosition();
    while (lastVal > -360 && lastVal < 360) {
      int newVal = motor.getPosition();
      if (newVal == lastVal) {
        Ev3Display.refresh();
        Wait.forMillis(100);
      }
      else {
        System.out.println("Wheel value = " + newVal);
        Ev3Display.drawString(true, "" + newVal, 60, 60, 30, true);
        Ev3Sound.say("Going " + (newVal < lastVal ? "down" : "up"), 100);
        lastVal = newVal;
      }
    }

    Ev3Sound.say("Goodbye", 100);
    System.out.println("Exiting");
  }
}
