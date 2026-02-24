import javax.management.RuntimeErrorException;
import java.util.Arrays;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {

        Arrays.sort(houses);

        double max = houses[houses.length - 1] - houses[0];
        double min = 0;

        while (max - min > 0.5) {

            double mid = min + (max - min) / 2;

            if (coverable(houses, numOfAccessPoints, mid)) {
                // Yes, coverable, try smaller radius

                max = mid;

            } else {
                // No, uncoverable, try larger radius

                min = mid;

            }

        }

        return max;
    }

    /**
     * Implement your solution here
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {

        if (houses == null || numOfAccessPoints <= 0 || distance < 0) {
            throw new RuntimeErrorException(new Error("One or more arguments are invalid."));
        }

        if (houses.length <= numOfAccessPoints) {
            return true;
        }

        double range = 2 * distance;

        int currentHouse = 0;
        int routersUsed = 1;

        for (int i = 1; i < houses.length; i++) {

            if (routersUsed > numOfAccessPoints) {
                return false; // Early exit
            }

            if (houses[currentHouse] + range >= houses[i]) {
                continue;
            } else {
                currentHouse = i;
                routersUsed++;
            }

        }

        return routersUsed <= numOfAccessPoints;
    }
}
