/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };

    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this

        if (dataArray == null || dataArray.length == 0) {
            //throw new IllegalArgumentException("Received array is null or empty");
            return 0;
        }

        int last = dataArray.length - 1;

        if (last == 0) {
            return dataArray[last];
        } else if (dataArray[0] > dataArray[1] && dataArray[last - 1] > dataArray[last]) {
            //strictly decreasing
            return dataArray[0];
        } else if (dataArray[0] < dataArray[1] && dataArray[last - 1] < dataArray[last]) {
            //strictly increasing
            return dataArray[last];
        } else if (dataArray[0] > dataArray[1] && dataArray[last - 1] < dataArray[last]) {
            //decrease then increase

            if (dataArray[0] >= dataArray[last]) { // Check if leftmost or rightmost is the biggest
                return dataArray[0];
            } else {
                return dataArray[last];
            }

        } else if (dataArray[0] < dataArray[1] && dataArray[last - 1] > dataArray[last]) {
            //increase_then_decrease

            int left = 0;
            int right = last;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (dataArray[mid] < dataArray[mid + 1]) {
                    //peak is to the right
                    left = mid + 1;
                } else {
                    //peak is to the left or at mid
                    right = mid;
                }

            }

            return dataArray[left];
        } else {
            //throw new RuntimeException("Received array violates contract.");
            return 0;
        }
    }

    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}