import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingTester {
    public static boolean checkSort(ISort sorter, int size) {

        if (size < 0) {
            throw new IllegalArgumentException(new Error("size cannot be a negative value."));
        } else if (size <= 1) {
            return true; // Empty and singleton arrays are considered sorted vacuously
        }

        KeyValuePair[] arr = new KeyValuePair[size];
        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = new KeyValuePair(rng.nextInt(1000), i); // Arbitrary number in tail of pairs
        }

        sorter.sort(arr);

        for (int j = 0; j < size - 1; j++) {
            if (arr[j].getKey() > arr[j + 1].getKey()) {
                return false;
            }
        }

        return true;
    }

    public static boolean isStable(ISort sorter, int size) {

        if (size < 0) {
            throw new IllegalArgumentException(new Error("size cannot be a negative value."));
        } else if (size <= 1) {
            return true; // Empty and singleton arrays are considered stable vacuously
        }

        KeyValuePair[] arr = new KeyValuePair[size];
        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = new KeyValuePair(rng.nextInt(2), i); // Mixed KeyValuePairs
        }

        sorter.sort(arr);

        for (int j = 0; j < size - 1; j++) {
            if (arr[j].getKey() == arr[j + 1].getKey() && arr[j].getValue() > arr[j + 1].getValue()) {
                return false;
            }
        }

        return true;
    }

    public static boolean isSorted(KeyValuePair[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getKey() > arr[i + 1].getKey()) {
                return false;
            }
        }

        return true;
    }

    public static boolean isStable2(KeyValuePair[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getValue() > arr[i + 1].getValue() && arr[i].getKey() == arr[i + 1].getKey()) {
                return false;
            }
        }

        return true;
    }

    public static void main2(){

        ISort[] sorters = {
                new SorterA(),
                new SorterB(),
                new SorterC(),
                new SorterD(),
                new SorterE()
        };

        Random rng = new Random();
        int size = rng.nextInt(10000, 30000);

        KeyValuePair[] random = new KeyValuePair[size]; // random array
        KeyValuePair[] sorted = new KeyValuePair[size]; // Forward sorted array, i.e. 1 -> 10
        KeyValuePair[] reverse = new KeyValuePair[size]; // Reverse sorted array, i.e. 10 -> 1
        KeyValuePair[] same = new KeyValuePair[size]; // Same Key array

        for (int i = 0; i < size; i++) {
            random[i] = new KeyValuePair(rng.nextInt(30000), i);
            sorted[i] = new KeyValuePair(i, i);
            reverse[i] = new KeyValuePair(size - i, i);
            same[i] = new KeyValuePair(20000, i);
        }

        int count = 0;
        String[] labels = { "random", "sorted", "reverse", "same" };

        KeyValuePair[][] arrays = {
                random,
                sorted,
                reverse,
                same
        };

        while(count <= sorters.length) {

            switch (count) {
                case 0:

                    System.out.println("\n");

                    for (int i = 0; i < arrays.length; i++) {
                        KeyValuePair[] copy = Arrays.copyOf(arrays[i], arrays[i].length);

                        System.out.printf("%s on %s: %d%n", sorters[count].getClass().getSimpleName(),
                                labels[i], sorters[count].sort(copy));
                        System.out.printf("Sorted? %s%n", Boolean.toString(isSorted(copy)));
                        System.out.printf("Stable? %s%n", Boolean.toString(isStable2(copy)));
                    }

                    count++;
                    break;
                case 1:

                    System.out.println("\n");

                    for (int i = 0; i < arrays.length; i++) {
                        KeyValuePair[] copy = Arrays.copyOf(arrays[i], arrays[i].length);

                        System.out.printf("%s on %s: %d%n", sorters[count].getClass().getSimpleName(),
                                labels[i], sorters[count].sort(copy));
                        System.out.printf("Sorted? %s%n", Boolean.toString(isSorted(copy)));
                        System.out.printf("Stable? %s%n", Boolean.toString(isStable2(copy)));
                    }

                    count++;
                    break;
                case 2:

                    System.out.println("\n");

                    for (int i = 0; i < arrays.length; i++) {
                        KeyValuePair[] copy = Arrays.copyOf(arrays[i], arrays[i].length);

                        System.out.printf("%s on %s: %d%n", sorters[count].getClass().getSimpleName(),
                                labels[i], sorters[count].sort(copy));
                        System.out.printf("Sorted? %s%n", Boolean.toString(isSorted(copy)));
                        System.out.printf("Stable? %s%n", Boolean.toString(isStable2(copy)));
                    }

                    count++;
                    break;
                case 3:

                    System.out.println("\n");

                    for (int i = 0; i < arrays.length; i++) {
                        KeyValuePair[] copy = Arrays.copyOf(arrays[i], arrays[i].length);

                        System.out.printf("%s on %s: %d%n", sorters[count].getClass().getSimpleName(),
                                labels[i], sorters[count].sort(copy));
                        System.out.printf("Sorted? %s%n", Boolean.toString(isSorted(copy)));
                        System.out.printf("Stable? %s%n", Boolean.toString(isStable2(copy)));
                    }

                    count++;
                    break;

                case 4:

                    System.out.println("\n");

                    for (int i = 0; i < arrays.length; i++) {
                        KeyValuePair[] copy = Arrays.copyOf(arrays[i], arrays[i].length);

                        System.out.printf("%s on %s: %d%n", sorters[count].getClass().getSimpleName(),
                                labels[i], sorters[count].sort(copy));
                        System.out.printf("Sorted? %s%n", Boolean.toString(isSorted(copy)));
                        System.out.printf("Stable? %s%n", Boolean.toString(isStable2(copy)));
                    }

                    count++;
                    break;
            }
        }
    }

    public static void findEvil() {
        ISort[] sorters = {
                new SorterA(),
                new SorterB(),
                new SorterC(),
                new SorterD(),
                new SorterE()
        };

        Random rng = new Random();
        int size = rng.nextInt(10000, 30000);

        System.out.println("\nRunning until unsorted array is returned.\n");
        boolean remain = true;
        int[] significance = {0, 0, 0, 0, 0};

        while (remain) {

            for (int j = 0; j < sorters.length; j++) {

                KeyValuePair[] random = new KeyValuePair[size]; // random array

                for (int i = 0; i < size; i++) {
                    random[i] = new KeyValuePair(rng.nextInt(10000, 30000), i);
                }

                sorters[j].sort(random);

                if (isSorted(random)) {
                    continue;
                } else if (!isSorted(random) && significance[j] < 5){
                    ++significance[j];
                    continue;
                } else {
                    System.out.println("Found Dr. Evil! " + sorters[j].getClass().getSimpleName());

                    remain = false;
                }

            }
        }
    }

    public static void correction() {

        ISort[] sorters = {
                new SorterA(),
                new SorterB(),
                new SorterC(),
                new SorterD(),
                new SorterE()
        };

        Random rng = new Random();
        int[] sizes = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 20000};
        KeyValuePair[][] random = new KeyValuePair[sizes.length][]; // random array of array

        for (int i = 0; i < sizes.length; i++) {

            int size = sizes[i];
            random[i] = new KeyValuePair[size];

            for (int j = 0; j < size; j++) {

                random[i][j] = new KeyValuePair(rng.nextInt(10000, 30000), j);
            }

        }

        for (ISort sorter : sorters) {

            long prevCost = -1;

            System.out.println("\nTesting " + sorter.getClass().getSimpleName() + "\n");

            for (int k = 0; k < random.length; k++) {

                KeyValuePair[] copy = Arrays.copyOf(random[k], random[k].length);

                long cost = sorter.sort(copy);

                if (prevCost != -1) {
                    double ratio = (double) cost / (double) prevCost;
                    prevCost = cost;
                    System.out.printf("Size: %d, Cost: %d, Ratio: %.2f%n", sizes[k], cost, ratio);
                } else {
                    prevCost = cost;
                    System.out.println(String.format("Size %d | Time %d", sizes[k], cost));
                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input: [findEvil/main2/correction]");
        String input = scanner.nextLine();

        switch (input) {
            case "findEvil":
                findEvil();
                break;

            case "main2":
                main2();
                break;

            case "correction":
                correction();
                break;

            default:
                main2();
                break;
        }

    }
}