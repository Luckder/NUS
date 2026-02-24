public interface HasQueue {
  boolean enq(Customer obj);

  @Override
  String toString();
}
