package ev34j.firebase.keyboard;

public class KeyboardData {

  private final long timeStamp = System.currentTimeMillis();

  private KeyType keyType;

  public KeyboardData() { }

  public KeyboardData(final KeyType keyType) {
    this.keyType = keyType;
  }

  public long getTimeStamp() { return this.timeStamp; }

  public KeyType getKeyType() { return this.keyType; }
}
