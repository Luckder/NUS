public class Account {

  private int value;

  public Account(int value) {
    this.value = value;
  }

  public int getBalance() {
    return this.value;
  }

  public void deposit(int sum) {
    this.value += sum;
  }

  public boolean withdraw(int sum) {

    if (this.value < sum) {
      return false;
    }

    this.value = this.value - sum;

    return true;
  }
}
