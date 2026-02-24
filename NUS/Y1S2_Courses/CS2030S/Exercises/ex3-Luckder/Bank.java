public class Bank implements HasQueue {

  private Seq<Counter> counters;
  private Queue<Customer> bankQueue;
  private int numOfCounters;

  public Bank(int numOfCounters, int maxSize, int maxCounterSize) {

    this.bankQueue = new Queue<Customer>(maxSize);
    this.numOfCounters = numOfCounters;
    this.counters = new Seq<Counter>(numOfCounters);

    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new Counter(i, maxCounterSize));
    }
  }

  public Counter findAvailableCounter() {
    for (int i = 0; i < this.numOfCounters; i++) {
      Counter counter = this.counters.get(i);

      if (counter.isAvailable()) {
        return counter;
      }
    }

    return null;
  }

  public Counter getShortestQueue() {
    return this.counters.min();
  }

  public boolean enq(Customer obj) {
    return this.bankQueue.enq(obj);
  }

  public Customer deq() {
    return (Customer) this.bankQueue.deq();
  }

  public boolean isFull() {
    return this.bankQueue.isFull();
  }

  public boolean isCountersFull() {

    boolean result = true;

    for (int i = 0; i < this.numOfCounters; i++) {
      Counter counter = this.counters.get(i);

      if (!counter.isFull()) {
        result = false;
      }
    }

    return result;
  }

  public boolean isEmpty() {
    return this.bankQueue.isEmpty();
  }

  public Queue<Customer> getQueue() {
    return this.bankQueue;
  }

  @Override
  public String toString() {
    return this.bankQueue.toString();
  }
}
