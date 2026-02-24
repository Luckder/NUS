public class ServiceEndEvent extends Event {

  private Bank bank;
  private Customer customer;
  private Counter counter;
  private String taskString;
  private String result;

  public ServiceEndEvent(double time, Bank bank, Customer customer, Counter counter) {

    super(time);
    this.bank = bank;
    this.customer = customer;
    this.counter = counter;
    this.taskString = "";
    this.result = "success";

    if (this.customer.getTask() == 0) {
      this.taskString += "deposit";
      this.counter.deposit(this.customer.getSum());
    } else {
      this.taskString += "withdrawal";
      boolean success = this.counter.withdraw(this.customer.getSum());

      if (success) {
        this.result = "success";
      } else {
        this.result = "fail";
      }
    }
  }

  @Override
  public Event[] simulate() {

    if (this.counter.isEmpty()) { // Counter queue is empty

      if (this.bank.isEmpty()) {
        this.counter.makeAvailable();

        return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
      } else { // Bank queue is not empty

        Customer nextCustomer = this.bank.deq();

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.bank, nextCustomer, this.counter)
        };
      }

    } else { // Counter queue is not empty

      if (this.bank.isEmpty()) {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.bank, this.counter.deq(), this.counter)
        };

      } else {

        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), this.bank, this.counter.deq(), this.counter),
          new MigrationEvent(this.getTime(), this.bank, this.counter)
        };
      }
    }
  }

  @Override
  public String toString() {

    String str =
        String.format(
            ": C%d %s of $%d (at S%d $%d %s) ends: %s",
            this.customer.getId(),
            this.taskString,
            this.customer.getSum(),
            this.counter.getId(),
            this.counter.getBalance(),
            this.counter.getQueue().toString(),
            this.result);

    return super.toString() + str;
  }
}
