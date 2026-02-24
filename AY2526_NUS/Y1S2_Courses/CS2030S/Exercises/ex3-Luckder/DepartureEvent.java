class DepartureEvent extends Event {

  private Customer customer;

  /**
   * Creates an event that occurs at the given time.
   *
   * @param time The time the event occurs.
   */
  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public Event[] simulate() {
    return new Event[0];
  }

  @Override
  public String toString() {
    String str = String.format(": C%d departs", this.customer.getId());

    return super.toString() + str;
  }
}
