package ev34j.firebase.keyboard;

public class RobotData {

  private final long timeStamp = System.currentTimeMillis();

  private String metric;
  private int    value;

  public RobotData() { }

  public RobotData(final String metric, final int value) {
    this.metric = metric;
    this.value = value;
  }

  public long getTimeStamp() { return this.timeStamp; }

  public String getMetric() { return this.metric; }

  public int getValue() { return this.value; }
}
