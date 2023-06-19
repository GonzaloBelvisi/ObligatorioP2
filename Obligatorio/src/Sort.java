import java.util.Comparator;

    public class Sort {

        public static <T extends Comparable<T>> void sortFirstElement(T[] array) {
            sortFirstElement(array, null);
        }

        public static <T> void sortFirstElement(T[] array, Comparator<T> comparator) {
            if (array.length <= 1) {
                return; // No need to sort if array has 0 or 1 elements
            }

            T firstElement = array[0];
            int i = 1;

            while (i < array.length && compare(firstElement, array[i], comparator) > 0) {
                array[i - 1] = array[i];
                i++;
            }

            array[i - 1] = firstElement;
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
    }

