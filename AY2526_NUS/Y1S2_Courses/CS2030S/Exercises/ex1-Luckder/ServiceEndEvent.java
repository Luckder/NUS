public class ServiceEndEvent extends Event {

    private Customer customer;
    private Counter counter;

    public ServiceEndEvent(double time, Customer customer, Counter counter) {

        super(time);
        this.customer = customer;
        this.counter = counter;

    }

    @Override
    public Event[] simulate() {

        counter.makeAvailable();

        return new Event[] { new DepartureEvent(this.getTime(), customer) };
    }

    @Override
    public String toString() {

        String str = String.format(": Customer %d service ends (at Counter %d)", customer.getId(), counter.getId());

        return super.toString() + str;

    }
}
