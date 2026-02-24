import java.util.ArrayList;

public class Bonus {

    public static ArrayList<Integer>[] greedy(int[] jobSizes, int p) {

        ArrayList<Integer>[] processors = new ArrayList[p];

        for (int g = 0; g < p; g++) {
            processors[g] = new ArrayList<>();
        }

        for (int i = 0; i < jobSizes.length; i++) {

            if (i < p) {
                processors[i].add(jobSizes[i]);
            } else {
                int minIndex = 0;
                int minSum = getSum(processors[0]);

                for (int j = 0; j < p; j++) {
                    int currentSum = getSum(processors[j]);
                    if (currentSum < minSum) {
                        minSum = currentSum;
                        minIndex = j;
                    }
                }

                processors[minIndex].add(jobSizes[i]);
            }

        }

        return processors;
    }

    public static int getSum(ArrayList<Integer> arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return sum;
    }
}