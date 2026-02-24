import javax.management.RuntimeErrorException;

class Sorter {

    public static void sortStrings(String[] arr) {
        // TODO: implement your sorting function here

        if (arr == null) { throw new RuntimeErrorException(new Error("Null Array!")); }

        int len = arr.length;

        if (len > 0 && (arr[0] == null || arr[0].length() < 2)) {
            throw new RuntimeErrorException(new Error("Invalid String Found!"));
        }

        //Insertion Sort
        for (int i = 1; i < len; i++) {

            if (arr[i] == null || arr[i].length() < 2) {
                throw new RuntimeErrorException(new Error("Invalid String Found!"));
            }

            String key = arr[i];
            int j = i - 1;

            while (j >= 0 && isGreaterThan(arr[j], key)) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

        }
    }

    public static boolean isGreaterThan(String str1, String str2) {
        return str1.substring(0, 2).compareTo(str2.substring(0, 2)) > 0;
    }
}