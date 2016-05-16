package ev34j.firebase.keyboard;

import com.ev34j.core.common.Platform;
import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static ev34j.firebase.keyboard.Constants.DEFAULT_ROBOT;
import static ev34j.firebase.keyboard.Constants.DEFAULT_USER;
import static ev34j.firebase.keyboard.Constants.LAST_KEYSTROKE;
import static ev34j.firebase.keyboard.Constants.METRICS;
import static ev34j.firebase.keyboard.Constants.POWER1;
import static ev34j.firebase.keyboard.Constants.POWER2;
import static java.lang.String.format;

public class KeyboardControlledRobot {

  public static void main(final String[] args) {
    final KeyboardControlledRobot robot = new KeyboardControlledRobot();
    Wait.forSecs(Integer.MAX_VALUE);
  }

  private final long           startTime = System.currentTimeMillis();
  private final SteeringMotors motors    = new SteeringMotors("A", "B");
  private final Executor       executor  = Executors.newSingleThreadExecutor();

  private int steering   = 0;
  private int power      = 0;
  private int lastPower1 = -1;
  private int lastPower2 = -1;

  private final Firebase firebase;

  public KeyboardControlledRobot() {
    this.firebase = new Firebase(Constants.FIREBASE_URL);
    this.firebase.getRoot()
                 .child(DEFAULT_USER)
                 .child(LAST_KEYSTROKE)
                 .addValueEventListener(
                     new ValueEventListener() {
                       @Override
                       public void onDataChange(final DataSnapshot dataSnapshot) {
                         final KeyboardData data = dataSnapshot.getValue(KeyboardData.class);
                         System.out.println("Type: " + data.getKeyType());
                         if (!Platform.isUnknown())
                           processKeyStroke(data);
                       }

                       @Override
                       public void onCancelled(final FirebaseError error) {
                         System.out.println(String.format("ValueEventListener.onCancelled() : %s", error.getMessage()));
                       }
                     });

    final Runnable runnable =
        new Runnable() {
          @Override
          public void run() {
            while (true) {
              // Do not send duplicate power values
              final int power1 = getMotors().getPower1();
              if (power1 != lastPower1) {
                reportMetricToFirebase(POWER1, power1);
                lastPower1 = power1;
              }

              final int power2 = getMotors().getPower2();
              if (power2 != lastPower2) {
                reportMetricToFirebase(POWER2, power2);
                lastPower2 = power2;
              }

              Wait.forMillis(500);
            }
          }
        };

    this.executor.execute(runnable);

    if (Platform.isEv3Brick())
      Ev3Sound.say("Ready", 100);
    System.out.println("Ready");
  }

  private Firebase getFirebase() { return this.firebase; }

  private SteeringMotors getMotors() { return this.motors; }

  private void reportMetricToFirebase(final String metric, final int value) {
    System.out.println(format("Writing data for %s: %d", metric, value));
    final CountDownLatch latch = new CountDownLatch(1);
    final Firebase.CompletionListener listener =
        new Firebase.CompletionListener() {
          @Override
          public void onComplete(final FirebaseError error, final Firebase firebase) {
            if (error != null)
              System.out.println(format("Data not writter: %s", error.getMessage()));
            latch.countDown();
          }
        };

    this.getFirebase()
        .getRoot()
        .child(DEFAULT_ROBOT)
        .child(METRICS)
        .child(metric)
        .setValue(new RobotData(metric, value), listener);
  }

  private void processKeyStroke(final KeyboardData data) {

    // Prevent acting on keystrokes that occur before startup
    if (data.getTimeStamp() < startTime)
      return;

    switch (data.getKeyType()) {
      case LOWER_S:
      case UPPER_S:
        this.power = 0;
        break;
      case UP_ARROW:
        this.power += 10;
        break;
      case DOWN_ARROW:
        this.power -= 10;
        break;
      case LEFT_ARROW:
        this.steering -= 10;
        break;
      case RIGHT_ARROW:
        this.steering += 10;
        break;
      default:
        // Ignore other keys
    }
    if (this.power == 0)
      this.motors.off();
    else
      this.motors.on(this.steering, this.power);
  }
}
