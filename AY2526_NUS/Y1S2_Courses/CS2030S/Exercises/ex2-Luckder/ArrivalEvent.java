class ArrivalEvent extends Event {

  private Customer customer;
  private Queue queue;
  private Bank bank;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   */
  public ArrivalEvent(double time, Customer customer, Bank bank, Queue queue) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.queue = queue;
  }

  @Override
  public Event[] simulate() {

    Counter counter = bank.findAvailableCounter();

    if (counter != null) {
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, counter, this.queue)
      };
    } else if (queue.isFull()) {
      return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
    } else {
      return new Event[] {new JoinQueue(this.getTime(), this.customer, this.queue)};
    }
  }

  @Override
  public String toString() {

    String str2 = String.format(": C%d arrives ", customer.getId()) + queue.toString();

    return super.toString() + str2;
  }
}
