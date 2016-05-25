import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.sound.Note;
import com.ev34j.mindstorm.time.Wait;

public class SpeachAndNotes {

  public static void main(String[] args) {

    Ev3Sound.say("I am a LEGO robot", 100);
    Wait.forSecs(1);
    Ev3Sound.say("Soy un robot LEGO", 100);
    Wait.forSecs(1);

    Ev3Sound.playNote(Note.C4, 1, 100);
    Ev3Sound.playNote(Note.C4, 1, 100);
    Ev3Sound.playNote(Note.B5, 1, 100);
    Wait.forSecs(1);

    // Play scale
    for (Note note : Note.values())
      Ev3Sound.playNote(note, 1, 100);
  }
}
