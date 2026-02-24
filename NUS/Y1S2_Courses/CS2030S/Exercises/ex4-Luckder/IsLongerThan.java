/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Exercise 4
 * AY25/26 Semester 2
 *
 * @author David Chan (14G)
 */
public class IsLongerThan implements BooleanCondition<String> {

  private int compareMe;

  public IsLongerThan(int compareMe) {
    this.compareMe = compareMe;
  }

  @Override
  public boolean test(String againstMe) {

    /*
    if (againstMe == null) {
      return false;
    }

    return againstMe.length() > compareMe;
    */

    // Recommended by submit.sh's linter
    return againstMe == null ? false : againstMe.length() > compareMe;
  }
}
