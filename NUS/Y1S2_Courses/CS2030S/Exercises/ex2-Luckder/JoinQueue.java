public class JoinQueue extends Event {

  private Queue queue;
  private Customer customer;

  public JoinQueue(double time, Customer customer, Queue queue) {

    super(time);
    this.queue = queue;
    this.customer = customer;
  }

  @Override
  public Event[] simulate() {

    boolean ignore = this.queue.enq(this.customer);

    if (ignore) {
      return new Event[0];
    }

    return new Event[0];
  }

  @Override
  public String toString() {

    String str =
        String.format(": C%d joins queue %s", this.customer.getId(), this.queue.toString());

    return super.toString() + str;
  }
}
