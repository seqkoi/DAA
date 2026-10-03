import java.util.Arrays;

class HybridSort {

    private static final int THRESHOLD = 10;

    public static void hybridSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {

        // Use insertion sort for small parts
        if (right - left + 1 <= THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        if (left < right) {
            int middle = left + (right - left) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= left && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            array[left + x] = temp[x];
        }
    }

    public static void main(String[] args) {

        int[] numbers = {38, 27, 43, 3, 9, 82, 10, 5, 21, 7, 15, 1};

        System.out.println("Before sorting:");
        System.out.println(Arrays.toString(numbers));

        hybridSort(numbers);

        System.out.println("After sorting:");
        System.out.println(Arrays.toString(numbers));
    }
}