public class Counter implements HasQueue, Comparable<Counter> {

  private int id;
  private boolean isAvailable;
  private Queue<Customer> queue;
  private Account account;

  public Counter(int id, int maxCounterSize) {
    this.id = id;
    this.isAvailable = true;

    this.queue = new Queue<Customer>(maxCounterSize);
    this.account = new Account(100);
  }

  @Override
  public int compareTo(Counter other) {

    if (this.isAvailable && !other.isAvailable) {
      return -1;
    }
    if (this.isAvailable && other.isAvailable) {
      return 1;
    }

    if (this.getQueueLength() != other.getQueueLength()) {
      return this.getQueueLength() - other.getQueueLength();
    }

    return this.id - other.id;
  }

  public int getId() {
    return this.id;
  }

  public void makeAvailable() {
    isAvailable = true;
  }

  public void makeUnavailable() {
    isAvailable = false;
  }

  public boolean isEmpty() {
    return this.queue.isEmpty();
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public Customer deq() {
    return (Customer) this.queue.deq();
  }

  public boolean enq(Customer obj) {
    return this.queue.enq(obj);
  }

  public boolean isFull() {
    return this.queue.isFull();
  }

  public int getQueueLength() {
    return this.queue.length();
  }

  public Queue<Customer> getQueue() {
    return this.queue;
  }

  public void deposit(int sum) {
    this.account.deposit(sum);
  }

  public boolean withdraw(int sum) {
    return this.account.withdraw(sum);
  }

  public int getBalance() {
    return this.account.getBalance();
  }

  @Override
  public String toString() {
    return String.format("S%d", this.id);
  }
}
