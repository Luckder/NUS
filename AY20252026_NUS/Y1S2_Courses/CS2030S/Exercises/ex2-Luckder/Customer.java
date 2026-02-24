public class Customer {

  private double serviceTime;
  private int id;
  private int task;

  public Customer(int id, double serviceTime, int task) {
    this.id = id;
    this.serviceTime = serviceTime;
    this.task = task;
  }

  public int getId() {
    return this.id;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public String getTask() {

    String str = "";

    if (this.task == 0) {
      str += "deposit";
    } else {
      str += "withdrawal";
    }

    return str;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }
}
