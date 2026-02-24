/**
 * CS2030S Exercise 0: Point.java
 * Semester 2, 2025/26
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author XXX
 */
class Point {
  private final double x;
  private final double y;

  public Point(double x, double y) {

    this.x = x;
    this.y = y;
  }

  public double distance_to(Point p) {

    double dx = this.x - p.x;
    double dy = this.y - p.y;

    return Math.sqrt(dx * dx + dy * dy);
  }

  @Override
  public String toString() {

    return "(" + this.x + ", " + this.y + ")";
  }
}
