/**
 * Takes an item and return the item in a box.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author David Chan (14G)
 */
public class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T item) {

    return new Box<T>(item);
  }
}
