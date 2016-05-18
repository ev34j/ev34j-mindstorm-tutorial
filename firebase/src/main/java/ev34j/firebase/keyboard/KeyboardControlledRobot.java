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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static ev34j.firebase.keyboard.Constants.ACTION;
import static ev34j.firebase.keyboard.Constants.DEFAULT_ROBOT;
import static ev34j.firebase.keyboard.Constants.DEFAULT_USER;
import static ev34j.firebase.keyboard.Constants.LAST_KEYSTROKE;
import static ev34j.firebase.keyboard.Constants.METRICS;
import static ev34j.firebase.keyboard.Constants.POSITION1;
import static ev34j.firebase.keyboard.Constants.POSITION2;
import static ev34j.firebase.keyboard.Constants.POWER1;
import static ev34j.firebase.keyboard.Constants.POWER2;
import static ev34j.firebase.keyboard.Constants.STEERING;
import static java.lang.String.format;

public class KeyboardControlledRobot {

  public static void main(final String[] args)
      throws InterruptedException {
    final KeyboardControlledRobot robot = new KeyboardControlledRobot();
    if (!Platform.isUnknown())
      Ev3Sound.say("Initialized", 100);
    System.out.println("Initialized");

    robot.waitUntilFinished();
    System.out.println("Exiting");
  }

  private final AtomicLong     startTime = new AtomicLong(-1);
  private final AtomicBoolean  exit      = new AtomicBoolean(false);
  private final CountDownLatch complete  = new CountDownLatch(1);
  private final Executor       executor  = Executors.newSingleThreadExecutor();

  private final Firebase.CompletionListener completionListener =
      new Firebase.CompletionListener() {
        @Override
        public void onComplete(final FirebaseError error, final Firebase firebase) {
          if (error != null)
            System.err.println(format("Data not writter: %s", error.getMessage()));
        }
      };

  private int steering      = 0;
  private int power         = 0;
  private int exitCommand   = 0;
  private int lastSteering  = Integer.MIN_VALUE;
  private int lastPower1    = Integer.MIN_VALUE;
  private int lastPower2    = Integer.MIN_VALUE;
  private int lastPosition1 = Integer.MIN_VALUE;
  private int lastPosition2 = Integer.MIN_VALUE;

  private final SteeringMotors motors;
  private final Firebase       firebase;

  public KeyboardControlledRobot() {
    this.motors = Platform.isUnknown() ? null : new SteeringMotors("A", "B");
    this.firebase = new Firebase(Constants.FIREBASE_URL);
    this.firebase.getRoot()
                 .child(DEFAULT_USER)
                 .child(LAST_KEYSTROKE)
                 .addValueEventListener(
                     new ValueEventListener() {
                       @Override
                       public void onDataChange(final DataSnapshot dataSnapshot) {
                         final KeyboardData data = dataSnapshot.getValue(KeyboardData.class);
                         if (startTime.get() == -1) {
                           startTime.set(System.currentTimeMillis());
                           if (Platform.isEv3Brick())
                             Ev3Sound.say("Processing keystrokes", 100);
                           System.out.println("Processing keystrokes");
                         }

                         processKeyStroke(data);
                       }

                       @Override
                       public void onCancelled(final FirebaseError error) {
                         System.out.println(String.format("ValueEventListener.onCancelled() : %s", error.getMessage()));
                       }
                     });

    if (Platform.isEv3Brick())
      this.executor.execute(
          new Runnable() {
            @Override
            public void run() {
              while (!exit.get()) {
                // Do not send duplicate  values
                final int steering1 = KeyboardControlledRobot.this.motors.getSteering();
                if (steering1 != lastSteering) {
                  reportMetric(STEERING, steering1);
                  lastSteering = steering1;
                }

                final int power1 = KeyboardControlledRobot.this.motors.getPower1();
                if (power1 != lastPower1) {
                  reportMetric(POWER1, power1);
                  lastPower1 = power1;
                }

                final int power2 = KeyboardControlledRobot.this.motors.getPower2();
                if (power2 != lastPower2) {
                  reportMetric(POWER2, power2);
                  lastPower2 = power2;
                }

                final int position1 = KeyboardControlledRobot.this.motors.getPosition1();
                if (position1 != lastPosition1) {
                  reportMetric(POSITION1, position1);
                  lastPosition1 = position1;
                }

                final int position2 = KeyboardControlledRobot.this.motors.getPosition2();
                if (position2 != lastPosition2) {
                  reportMetric(POSITION2, position2);
                  lastPosition2 = position2;
                }

                Wait.forMillis(500);
              }

              System.out.println("Discontinued metric reporting");
              complete.countDown();
            }
          });

    if (this.motors != null)
      this.motors.reset();
  }

  private Firebase getFirebase() { return this.firebase; }

  private CountDownLatch getComplete() { return this.complete; }

  private void reportMetric(final String metric, final int value) {
    this.getFirebase()
        .getRoot()
        .child(DEFAULT_ROBOT)
        .child(METRICS)
        .child(metric)
        .setValue(new RobotMetric(metric, value), this.completionListener);
  }

  private void reportAction(final String action) {
    this.getFirebase()
        .getRoot()
        .child(DEFAULT_ROBOT)
        .child(ACTION)
        .setValue(new RobotAction(action), this.completionListener);
  }

  private void processKeyStroke(final KeyboardData data) {
    // System.out.println("Type: " + data.getKeyType());
    // Prevent acting on keystrokes that occur before startup
    if (Platform.isUnknown() || data.getTimeStamp() + 5000 < startTime.get())
      return;

    switch (data.getKeyType()) {
      // Exit after two presses in a row
      case LOWER_X:
      case UPPER_X:
        this.exitCommand++;
        if (this.exitCommand >= 2) {
          this.reportAction("Exiting");
          this.motors.off();
          this.exit.set(true);
        }
        break;
      case LOWER_H:
      case UPPER_H:
        this.reportAction("Halt motors");
        this.power = 0;
        this.updatePower();
        break;
      case LOWER_S:
      case UPPER_S:
        this.reportAction("Go straight");
        this.steering = 0;
        this.updatePower();
        break;
      case LOWER_R:
      case UPPER_R:
        this.reportAction("Reset motors");
        this.steering = 0;
        this.power = 0;
        this.updatePower();
        this.motors.reset();
        break;
      case UP_ARROW:
        if (this.power <= 90) {
          this.reportAction("Power up 10%");
          this.power += 10;
          this.updatePower();
        }
        break;
      case DOWN_ARROW:
        if (this.power >= -90) {
          this.reportAction("Power down 10%");
          this.power -= 10;
          this.updatePower();
        }
        break;
      case LEFT_ARROW:
        if (this.steering >= -90) {
          this.reportAction("Turn left 10%");
          this.steering -= 10;
          this.updateSteering();
        }
        break;
      case RIGHT_ARROW:
        if (this.steering <= 90) {
          this.reportAction("Turn right 10%");
          this.steering += 10;
          this.updateSteering();
        }
        break;
      case SHIFT_UP_ARROW:
        if (this.power <= 80) {
          this.reportAction("Power up 20%");
          this.power += 20;
          this.updatePower();
        }
        break;
      case SHIFT_DOWN_ARROW:
        if (this.power >= -80) {
          this.reportAction("Power down 20%");
          this.power -= 20;
          this.updatePower();
        }
        break;
      case SHIFT_LEFT_ARROW:
        if (this.steering >= -80) {
          this.reportAction("Turn left 20%");
          this.steering -= 20;
          this.updateSteering();
        }
        break;
      case SHIFT_RIGHT_ARROW:
        if (this.steering <= 80) {
          this.reportAction("Turn right 20%");
          this.steering += 20;
          this.updateSteering();
        }
        break;
      default:
        // Ignore other keys
    }
  }

  private void updateSteering() {
    this.exitCommand = 0;
    this.motors.on(this.steering, this.power);
  }

  private void updatePower() {
    this.exitCommand = 0;
    if (this.power == 0)
      this.motors.off();
    else
      this.motors.on(this.steering, this.power);
  }

  private void waitUntilFinished()
      throws InterruptedException {
    this.getComplete().await();
  }
}
