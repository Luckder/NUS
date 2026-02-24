/**
 * A generic box storing an item.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author David Chan (14G)
 */
public class Box<T> {

  private final T item;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  public Box(T item) {

    this.item = item;
  }

  public T getItem() {
    return this.item;
  }

  @Override
  public boolean equals(Object other) {

    if (other == null || !(other instanceof Box)) {
      return false;
    }

    return this.item == null
        ? ((Box) other).getItem() == null ? true : false
        : this.item.equals(((Box) other).getItem());
  }

  @Override
  public String toString() {
    return this.item == null ? "[]" : String.format("[%s]", this.item);
  }

  public static <T> Box<T> of(T item) {

    if (item == null) {
      return null;
    }

    return new Box<T>(item);
  }

  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty() {

    return (Box<T>) EMPTY_BOX;
  }

  public boolean isPresent() {
    return this.item != null;
  }

  public static <T> Box<T> ofNullable(T item) {

    if (item != null) {
      return new Box<T>(item);
    }

    return Box.empty();
  }

  public Box<T> filter(BooleanCondition<T> pred) {

    if (this.item == null) {
      return new Box<>(null);
    }

    return pred.test(this.item) ? this : Box.empty();
  }

  public <U> Box<U> map(Transformer<T, U> transformer) {

    if (this.item == null) {
      return new Box<U>(null);
    }

    U result = transformer.transform(this.item);

    if (result == null) {
      return new Box<U>(null);
    }

    return new Box<U>(result);
  }
}
