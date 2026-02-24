public class Bank {

  private Counter[] counters;

  public Bank(int numOfCounters) {

    this.counters = new Counter[numOfCounters];

    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }
  }

  public Counter findAvailableCounter() {
    for (Counter counter : counters) {
      if (counter.isAvailable()) {
        return counter;
      }
    }

    return null;
  }
}
