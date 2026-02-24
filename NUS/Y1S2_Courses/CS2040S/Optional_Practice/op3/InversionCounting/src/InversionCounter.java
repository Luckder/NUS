import javax.management.RuntimeErrorException;

class InversionCounter {

    public static long countSwaps(int[] arr) {
        int len = arr.length;
        if (len <= 1) return 0;

        long totalSwaps = 0;

        // Bottom-up iterative mergesort
        for (int size = 1; size < len; size *= 2) {
            for (int left = 0; left < len - 1; left += 2 * size) {

                int left1 = left;
                int right1 = Math.min(left + size - 1, len - 1);
                int left2 = left + size;
                int right2 = Math.min(left + 2 * size - 1, len - 1);

                if (left2 < len) {
                    totalSwaps += mergeAndCount(arr, left1, right1, left2, right2);
                }
            }
        }

        return totalSwaps;
    }

    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        int[] temp = new int[right2 - left1 + 1];

        // Copy to temp
        for (int i = 0; i < temp.length; i++) {
            temp[i] = arr[left1 + i];
        }

        int i = 0;                      // Pointer in temp for left subarray
        int j = left2 - left1;     // Pointer in temp for right subarray
        int k = left1;                   // Pointer in original array
        long swaps = 0;

        int leftSize = right1 - left1 + 1;

        while (i < leftSize && j < temp.length) {
            if (temp[i] <= temp[j]) {

                arr[k] = temp[i];
                i += 1;

            } else {

                arr[k] = temp[j];
                j += 1;
                swaps += (leftSize - i);

            }
            k += 1;
        }

        while (i < leftSize) {
            arr[k] = temp[i];
            k += 1;
            i += 1;
        }
        while (j < temp.length) {
            arr[k] = temp[j];
            k += 1;
            j += 1;
        }

        return swaps;
    }
}
