import java.util.Comparator;

public class QuickSort {

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, null);
    }

    public static <T> void quickSort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSortRecursive(array, 0, array.length - 1, comparator);
    }

    private static <T> void quickSortRecursive(T[] array, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right, comparator);
        quickSortRecursive(array, left, pivotIndex - 1, comparator);
        quickSortRecursive(array, pivotIndex + 1, right, comparator);
    }

    private static <T> int partition(T[] array, int left, int right, Comparator<T> comparator) {
        T pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (compare(array[j], pivot, comparator) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return i + 1;
    }

    private static <T> int compare(T a, T b, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(a, b);
        }
        if (a instanceof Comparable && b instanceof Comparable) {
            return ((Comparable<T>) a).compareTo(b);
        }
        throw new IllegalArgumentException("Objects do not implement Comparable or a custom comparator is not provided");
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
