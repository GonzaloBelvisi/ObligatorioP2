import Entidades.User;
import Entidades.pilotoActivo;

public class SortForTop10Pilot {

    public static void quicksort(pilotoActivo[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(pilotoActivo[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(pilotoActivo[] arr, int low, int high) {
        int pivot = arr[high].getCantidadDeOcurrencias();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getCantidadDeOcurrencias() <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(pilotoActivo[] arr, int i, int j) {
        pilotoActivo temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Example usage
        pilotoActivo[] pilots = new pilotoActivo[10];
        // Populate the pilots array with active pilot objects

        quicksort(pilots);

        // Print the top 10 pilots based on the number of tweets
        for (int i = pilots.length - 1; i >= pilots.length - 10; i--) {
            System.out.println(pilots[i]);
        }
    }
}

