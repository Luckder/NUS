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

        Counter counter = bank.findAvailableCounter();

        if (counter != null) {
            return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer, counter) };
        }

        return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
    }

    @Override
    public String toString() {

        String str = String.format(": Customer %d arrives", customer.getId());

        return super.toString() + str;

    }
}
