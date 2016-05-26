import com.ev34j.mindstorms.sound.Ev3Sound;
import com.ev34j.mindstorms.sound.Note;
import com.ev34j.mindstorms.time.Wait;

public class SpeechAndNotes {

  public static void main(String[] args) {

    Ev3Sound.say("I am a LEGO robot", 100);
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
