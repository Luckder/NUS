public class JoinQueue extends Event {

  private Customer customer;
  private HasQueue banter;

  public JoinQueue(double time, Customer customer, HasQueue banter) {
    super(time);
    this.banter = banter;
    this.customer = customer;
  }

  @Override
  public Event[] simulate() {

    boolean ignore = this.banter.enq(this.customer);

    if (ignore) {
      return new Event[0];
    }

    return new Event[0];
  }

  @Override
  public String toString() {

    String str = "";

    if (this.banter instanceof Bank) {
      str +=
          String.format(": C%d joins bank queue %s", this.customer.getId(), this.banter.toString());
    } else if (this.banter instanceof Counter) {
      Counter counter = (Counter) this.banter;
      str +=
          String.format(
              ": C%d joins counter queue (at S%d $%d %s)",
              this.customer.getId(),
              counter.getId(),
              counter.getBalance(),
              counter.getQueue().toString());
    }

    return super.toString() + str;
  }
}
