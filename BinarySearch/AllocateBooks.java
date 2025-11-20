package BinarySearch;

public class AllocateBooks {

    public static int countOfStudents(int[] arr, int maxPages) {
        int students = 1;
        int noOfPages = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] + noOfPages <= maxPages) {
                noOfPages += arr[i];
            } else {
                students++;
                noOfPages = arr[i];
            }
        }

        return students;
    }

    public static int allocateBooksBrute(int[] arr, int m) {
        int ans = -1;

        if (arr.length < m) {
            return ans;
        }

        int max = 0; // the maximum number of pages can be all the pages of the book
        int low = 0;// the lowest pages is the highest pages of any of the book such that every
                    // student atleast gets 1 book

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > low) {
                low = arr[i];
            }

            max += arr[i];
        }

        // now go from low to max and check for each value how many students are
        // required if students required <= m then return that value this means
        // we found our answer
        for (int i = low; i <= max; i++) {
            int students = countOfStudents(arr, i);

            if (students <= m) {
                return i;
            } else {
                ans = i;
            }
        }

        return ans;
    }

    public static int allocateBooks(int[] arr, int m) {
        int ans = -1;

        if (arr.length < m)
            return ans;

        int low = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > low) {
                low = arr[i];
            }

            max += arr[i];
        }

        while (low <= max) {
            int mid = (low + max) / 2;

            int students = countOfStudents(arr, mid);

            if (students <= m) {
                ans = mid;
                max = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 12, 34, 67, 90};
        int students = 2;
        System.out.println(allocateBooks(arr, students));
    }
}
