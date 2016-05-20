import com.ev34j.core.common.Platform;
import com.ev34j.mindstorm.leds.BrickPiStatusLight;
import com.ev34j.mindstorm.leds.BrickPiStatusLights;
import com.ev34j.mindstorm.leds.Ev3StatusLight;
import com.ev34j.mindstorm.leds.Ev3StatusLights;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;

public class StatusLights {

  public static void main(String[] args) {

    if (Platform.isEv3Brick()) {
      Ev3StatusLight left = Ev3StatusLights.left();
      Ev3StatusLight right = Ev3StatusLights.right();

      Ev3Sound.say("Red", 100);
      left.red();
      right.red();
      Wait.forSecs(2);

      Ev3Sound.say("Green", 100);
      left.green();
      right.green();
      Wait.forSecs(2);

      Ev3Sound.say("Orange", 100);
      left.orange();
      right.orange();
      Wait.forSecs(2);

      Ev3Sound.say("Off", 100);
      left.off();
      right.off();
      Wait.forSecs(2);

      // Turn the leds green when complete
      left.green();
      right.green();
    }

    if (Platform.isBrickPi()) {
      BrickPiStatusLight left = BrickPiStatusLights.left();
      BrickPiStatusLight right = BrickPiStatusLights.right();

      left.on();
      right.on();
      Wait.forSecs(2);

      left.off();
      right.off();
      Wait.forSecs(2);

      flashBrickPi(left);
      flashBrickPi(right);

      left.off();
      right.off();
      System.out.println("Left light on: " + left.isOn());
      System.out.println("Right light on: " + right.isOn());
      Wait.forSecs(2);

      left.on();
      right.on();
      System.out.println("Left light on: " + left.isOn());
      System.out.println("Right light on: " + right.isOn());
      Wait.forSecs(2);
    }
  }

  private static void flashBrickPi(final BrickPiStatusLight light) {
    for (int i = 0; i < 40; i++) {
      light.on();
      Wait.forMillis(20);
      light.off();
      Wait.forMillis(20);
    }
  }
}
