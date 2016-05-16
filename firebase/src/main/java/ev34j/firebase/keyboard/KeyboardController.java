package ev34j.firebase.keyboard;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;

import static ev34j.firebase.keyboard.Constants.DEFAULT_ROBOT;
import static ev34j.firebase.keyboard.Constants.METRICS;
import static java.lang.String.format;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class KeyboardController {

  private static final String KEY_PRESSED = "Key pressed:";
  private static final String POWER1      = "Power1:";
  private static final String POWER2      = "Power2:";

  public static void main(final String[] args) {

    final Firebase firebase = new Firebase(Constants.FIREBASE_URL);

    final JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(10, 1, 5, 5));

    final JLabel keyPressed = new JLabel();
    final JLabel power1 = new JLabel();
    final JLabel power2 = new JLabel();

    keyPressed.setText(KEY_PRESSED);
    power1.setText(POWER1);
    power2.setText(POWER2);

    panel.add(keyPressed);
    panel.add(power1);
    panel.add(power2);

    for (final KeyType type : KeyType.values()) {
      panel.getActionMap()
           .put(type,
                new AbstractAction() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                    keyPressed.setText(format("%s %s", KEY_PRESSED, type.name()));
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

                    firebase.getRoot()
                            .child(Constants.DEFAULT_USER)
                            .child(Constants.LAST_KEYSTROKE)
                            .setValue(new KeyboardData(type), listener);
                  }
                });
      panel.getInputMap().put(KeyStroke.getKeyStroke(type.getKeyCode(), type.getModifiers()), type);
    }

    firebase.getRoot()
            .child(DEFAULT_ROBOT)
            .child(METRICS)
            .child(POWER1)
            .addValueEventListener(
                new ValueEventListener() {
                  @Override
                  public void onDataChange(final DataSnapshot dataSnapshot) {
                    final RobotData data = dataSnapshot.getValue(RobotData.class);
                    if (data != null) {
                      System.out.println(format("%s: %d", data.getMetric(), data.getValue()));
                      if (data.getMetric().equals(POWER1))
                        power1.setText("" + data.getValue());
                      if (data.getMetric().equals(POWER2))
                        power2.setText("" + data.getValue());
                    }
                  }

                  @Override
                  public void onCancelled(final FirebaseError error) {
                    System.out.println(String.format("ValueEventListener.onCancelled() : %s", error.getMessage()));
                  }
                });

    final JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}
