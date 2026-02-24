/**
 * A transformer with a parameter k that takes in an object x
 * and outputs the last k digits of the hash value of x.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author David Chan (14G)
 */
public class LastDigitsOfHashCode<T> implements Transformer<T, Integer> {

  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  @Override
  public Integer transform(T item) {

    int hash = Math.abs(item.hashCode());
    int divisor = (int) Math.pow(10, k);

    int result = hash % divisor;

    return result;
  }
}
