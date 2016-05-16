package ev34j.firebase.keyboard;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;

import static java.lang.String.format;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class KeyboardController {

  public static final  String FIREBASE_URL   = "https://ev34j-mindstorm.firebaseio.com";
  public static final  String LAST_KEYSTROKE = "lastKeystroke";
  public static final  String DEFAULT_USER   = "default_user";
  private static final String PREFIX         = "Key pressed:";

  public static void main(final String[] args) {

    final Firebase fb = new Firebase(FIREBASE_URL);

    final JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());

    final JLabel keyPressed = new JLabel();
    keyPressed.setText(PREFIX);
    panel.add(keyPressed);

    for (final KeyType type : KeyType.values()) {
      panel.getActionMap().put(type,
                               new AbstractAction() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                   keyPressed.setText(format("%s %s", PREFIX, type.name()));
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

                                   fb.child(DEFAULT_USER)
                                     .child(LAST_KEYSTROKE)
                                     .setValue(new KeyboardData(type), listener);
                                 }
                               });
      panel.getInputMap().put(KeyStroke.getKeyStroke(type.getKeyCode(), type.getModifiers()), type);
    }

    final JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}
