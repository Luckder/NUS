/**
 * The Seq<T> class for CS2030S
 *
 * @author XXX
 * @version CS2030S AY25/26 Semester 2
 */
class Seq<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] seq;

  public Seq(int size) {
    // TODO
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.seq = temp;
  }

  public void set(int index, T item) {
    // TODO
    this.seq[index] = item;
  }

  public T get(int index) {
    // TODO
    return this.seq[index];
  }

  public T min() {
    // TODO
    T minimum = this.seq[0];

    for (int i = 1; i < this.seq.length; i++) {

      if (this.seq[i].compareTo(minimum) < 0) {
        minimum = this.seq[i];
      }
    }

    return minimum;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.seq.length; i++) {
      s.append(i + ":" + this.seq[i]);
      if (i != this.seq.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
