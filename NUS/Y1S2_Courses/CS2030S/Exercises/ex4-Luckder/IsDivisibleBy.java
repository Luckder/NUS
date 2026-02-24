/**
 * A boolean condition with an integer parameter y that can be
 * apply to another integer x.  Returns true if x is divisible
 * by y, false otherwise.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author David Chan (14G)
 */
public class IsDivisibleBy implements BooleanCondition<Integer> {

  private Integer denom;

  public IsDivisibleBy(int denom) {
    this.denom = denom;
  }

  @Override
  public boolean test(Integer num) {

    /*
    if (num == null) {
      return false;
    }

    return num % denom == 0;
    */

    // Recommended by submit.sh's linter
    return num == null ? false : num % denom == 0;
  }
}
