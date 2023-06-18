import Entidades.User;

public class sortForTweetCount {
    public static void quicksort(User[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(User[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(User[] arr, int low, int high) {
        int pivot = arr[high].getNumberOfTweets();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getNumberOfTweets() <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(User[] arr, int i, int j) {
        User temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}

