public class ServiceBeginEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Queue queue;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   */
  public ServiceBeginEvent(double time, Customer customer, Counter counter, Queue queue) {

    super(time);
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }

  @Override
  public Event[] simulate() {

    counter.makeUnavailable();

    double endTime = this.getTime() + customer.getServiceTime();

    return new Event[] {new ServiceEndEvent(endTime, this.customer, this.counter, this.queue)};
  }

  @Override
  public String toString() {

    String task = this.customer.getTask();

    String str = String.format(": C%d %s (at S%d) begins", customer.getId(), task, counter.getId());

    return super.toString() + str;
  }
}
