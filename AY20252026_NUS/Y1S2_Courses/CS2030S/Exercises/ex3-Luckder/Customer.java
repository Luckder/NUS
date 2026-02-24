public class Customer {

  private double serviceTime;
  private int id;
  private int task;
  private int sum;

  public Customer(int id, double serviceTime, int task, int sum) {
    this.id = id;
    this.serviceTime = serviceTime;
    this.task = task;
    this.sum = sum;
  }

  public int getId() {
    return this.id;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public int getTask() {
    return this.task;
  }

  public int getSum() {
    return this.sum;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }
}
