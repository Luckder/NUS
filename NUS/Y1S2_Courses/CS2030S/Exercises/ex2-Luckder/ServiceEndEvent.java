public class ServiceEndEvent extends Event {

  private Customer customer;
  private Counter counter;
  private Queue queue;

  public ServiceEndEvent(double time, Customer customer, Counter counter, Queue queue) {

    super(time);
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }

  @Override
  public Event[] simulate() {

    if (queue.isEmpty()) {

      this.counter.makeAvailable();

      return new Event[] {new DepartureEvent(this.getTime(), this.customer)};

    } else {

      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), (Customer) queue.deq(), this.counter, this.queue)
      };
    }
  }

  @Override
  public String toString() {

    String task = this.customer.getTask();

    String str = String.format(": C%d %s (at S%d) ends", customer.getId(), task, counter.getId());

    return super.toString() + str;
  }
}
