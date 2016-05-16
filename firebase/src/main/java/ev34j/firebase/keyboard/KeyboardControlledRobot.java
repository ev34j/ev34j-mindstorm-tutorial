package ev34j.firebase.keyboard;

import com.ev34j.core.common.Platform;
import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class KeyboardControlledRobot {

  public static void main(final String[] args) {

    final Firebase fb = new Firebase(KeyboardController.FIREBASE_URL);
    final KeyboardControlledRobot robot = new KeyboardControlledRobot();

    final ValueEventListener valueEventListener =
        new ValueEventListener() {
          @Override
          public void onDataChange(final DataSnapshot dataSnapshot) {
            final KeyboardData data = dataSnapshot.getValue(KeyboardData.class);
            System.out.println("Type: " + data.getKeyType());
            if (Platform.isEv3Brick())
              Ev3Sound.say("Type: " + data.getKeyType(), 100);

            if (!Platform.isUnknown())
              robot.processKeyStroke(data);
          }

          @Override
          public void onCancelled(final FirebaseError error) {
            System.out.println(String.format("ValueEventListener.onCancelled() : %s", error.getMessage()));
          }
        };

    fb.child(KeyboardController.DEFAULT_USER)
      .child(KeyboardController.LAST_KEYSTROKE)
      .addValueEventListener(valueEventListener);

    if (Platform.isEv3Brick())
      Ev3Sound.say("Ready", 100);

    System.out.println("Ready");
    Wait.secs(Integer.MAX_VALUE);
  }

  private final SteeringMotors motors   = new SteeringMotors("A", "B");
  private       int            steering = 0;
  private       int            power    = 0;

  public KeyboardControlledRobot() {
  }

  private void processKeyStroke(final KeyboardData data) {
    switch (data.getKeyType()) {
      case UP_ARROW:
        this.power += 10;
        break;
      case DOWN_ARROW:
        this.power -= 10;
        break;
      case LEFT_ARROW:
        this.power -= 10;
        break;
      case RIGHT_ARROW:
        this.power += 10;
        break;
      default:
        // Ignore other key
    }
    if (this.power == 0)
      this.motors.off();
    else
      this.motors.on(this.steering, this.power);

  }

}
