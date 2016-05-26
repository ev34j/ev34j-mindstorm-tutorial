import com.ev34j.core.system.Platform;
import com.ev34j.mindstorms.display.Ev3Display;
import com.ev34j.mindstorms.sound.Ev3Sound;
import com.ev34j.mindstorms.time.Timer;
import com.ev34j.mindstorms.time.Wait;

public class HelloWorld {
  public static void main(String[] args) {

    // Print the message to stdout
    System.out.println("Hello World!");

    if (Platform.isEv3Brick()) {
      // Speak the message
      Ev3Sound.say("Hello World", 100);

      // Display the message. Warning: the initial call to Ev3Display is very slow on the EV3
      Ev3Display.drawString(true, "Hello World!", 25, 70, 20, true);

      // Flash the message for 10 seconds
      Timer t = new Timer(10);
      while (!t.isElapsed()) {
        Ev3Display.reverse();
        Wait.forMillis(200);
      }
    }
  }
}
