class ArrivalEvent extends Event {

  private Customer customer;
  private Bank bank;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   */
  public ArrivalEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public Event[] simulate() {

    Counter counter = this.bank.findAvailableCounter();

    if (counter != null) { // If there is an available counter
      return new Event[] {new ServiceBeginEvent(this.getTime(), this.bank, this.customer, counter)};
    } else if (!this.bank.isCountersFull()) { // If there is an available counter queue
      Counter shortestCounter = this.bank.getShortestQueue();
      return new Event[] {new JoinQueue(this.getTime(), this.customer, shortestCounter)};
    } else if (!this.bank.isFull()) { // If there is an available bank entrance queue
      return new Event[] {new JoinQueue(this.getTime(), this.customer, this.bank)};
    } else { // If there is not any queues available at all
      return new Event[] {new DepartureEvent(this.getTime(), this.customer)};
    }
  }

  @Override
  public String toString() {

    String str2 =
        String.format(": C%d arrives ", customer.getId()) + this.bank.getQueue().toString();

    return super.toString() + str2;
  }
}
