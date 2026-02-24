public class MigrationEvent extends Event {

  private Customer customer;
  private Bank bank;
  private Counter counter;

  public MigrationEvent(double time, Bank bank, Counter counter) {
    super(time + 0.01);
    this.bank = bank;
    this.counter = counter;
    this.customer = this.bank.deq();
  }

  @Override
  public Event[] simulate() {

    boolean ignore = this.counter.enq(this.customer);

    if (ignore) {
      return new Event[0];
    }

    return new Event[0];
  }

  @Override
  public String toString() {

    String str =
        String.format(
            ": C%d joins counter queue (at S%d $%d %s)",
            this.customer.getId(),
            this.counter.getId(),
            this.counter.getBalance(),
            this.counter.getQueue().toString());

    return super.toString() + str;
  }
}
