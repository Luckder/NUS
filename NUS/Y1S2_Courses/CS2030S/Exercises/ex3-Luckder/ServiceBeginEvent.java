public class ServiceBeginEvent extends Event {

  private Bank bank;
  private Customer customer;
  private Counter counter;
  private String taskString;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   */
  public ServiceBeginEvent(double time, Bank bank, Customer customer, Counter counter) {

    super(time);
    this.bank = bank;
    this.customer = customer;
    this.counter = counter;
    this.taskString = "";

    if (this.customer.getTask() == 0) {
      taskString += "deposit";
    } else {
      taskString += "withdrawal";
    }
  }

  @Override
  public Event[] simulate() {

    counter.makeUnavailable();

    double endTime = this.getTime() + customer.getServiceTime();

    return new Event[] {new ServiceEndEvent(endTime, this.bank, this.customer, this.counter)};
  }

  @Override
  public String toString() {

    String str =
        String.format(
            ": C%d %s of $%d (at S%d $%d %s) begins",
            customer.getId(),
            this.taskString,
            this.customer.getSum(),
            this.counter.getId(),
            this.counter.getBalance(),
            this.counter.getQueue().toString());

    return super.toString() + str;
  }
}
