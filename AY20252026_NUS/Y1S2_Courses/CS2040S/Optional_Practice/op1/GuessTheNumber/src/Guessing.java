import javax.management.RuntimeErrorException;

public class Guessing {

    // Your local variables here
    private int low = 0;
    private int high = 1000;
    private int mid = 0;

    /**
     * Implement how your algorithm should make a guess here
     */
    public int guess() {
        mid = low + (high - low) / 2;

        return mid;
    }

    /**
     * Implement how your algorithm should update its guess here
     */
    public void update(int answer) {
        if (answer == -1) {
            // Too Low, Go Higher
            low = mid + 1;
        } else if (answer == 1) {
            // Too High, Go Lower
            high = mid - 1;
        } else {
            throw new RuntimeErrorException(new Error("Bruh"));
        }
    }
}
