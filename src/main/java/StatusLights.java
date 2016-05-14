import com.ev34j.mindstorm.leds.Ev3StatusLight;
import com.ev34j.mindstorm.leds.Ev3StatusLights;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;

public class StatusLights {

  public static void main(String[] args) {

    Ev3StatusLight left = Ev3StatusLights.left();
    Ev3StatusLight right = Ev3StatusLights.right();

    Ev3Sound.sayAsEnglish("Red", 100);
    left.red();
    right.red();
    Wait.secs(2);

    Ev3Sound.sayAsEnglish("Green", 100);
    left.green();
    right.green();
    Wait.secs(2);

    Ev3Sound.sayAsEnglish("Orange", 100);
    left.orange();
    right.orange();
    Wait.secs(2);

    Ev3Sound.sayAsEnglish("Off", 100);
    left.off();
    right.off();
    Wait.secs(2);

    // Turn the leds green when complete
    left.green();
    right.green();
  }
}
