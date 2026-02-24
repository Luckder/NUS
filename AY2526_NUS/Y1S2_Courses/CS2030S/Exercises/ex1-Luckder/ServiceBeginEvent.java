public class ServiceBeginEvent extends Event {

    private Customer customer;
    private Counter counter;

    /**
     * Creates an event that occurs at the given time.
     *
     * @param time The time the event occurs.
     */
    public ServiceBeginEvent(double time, Customer customer, Counter counter) {

        super(time);
        this.customer = customer;
        this.counter = counter;

    }

    @Override
    public Event[] simulate() {

        counter.makeUnavailable();

        double endTime = this.getTime() + customer.getServiceTime();

        return new Event[] { new ServiceEndEvent(endTime, customer, counter) };
    }

    @Override
    public String toString() {

        String str = String.format(": Customer %d service begins (at Counter %d)", customer.getId(), counter.getId());

        return super.toString() + str;

    }
}
