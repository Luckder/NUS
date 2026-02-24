import java.util.Scanner;

/**
 * CS2030S Exercise 0: Estimating Pi with Monte Carlo
 * Semester 2, 2025/26
 *
 * <p>This program takes in two command line arguments: the
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX (00X)
 */
class Main {

  // TODO
  public static double estimatePi(int numOfPoints, int seed) {

    Circle circle = new Circle(new Point(0.5, 0.5), 0.5);

    long count = 0;

    RandomPoint.setSeed(seed);

    for (int i = 0; i < numOfPoints; i++) {

      Point p = new RandomPoint(0.0, 1.0, 0.0, 1.0);

      if (circle.contains(p)) {
        count++;
      }
    }

    return 4.0 * count / numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
