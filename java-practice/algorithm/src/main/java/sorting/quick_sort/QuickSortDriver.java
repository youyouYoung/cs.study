package sorting.quick_sort;

/**
 * 功能描述: 快排驱动类
 *
 * @author youyou
 * @date 11/10/19 8:47 PM
 */
public class QuickSortDriver {

    private static QuickSortStrategy quickSortStrategy = new QuickSortStrategyByBooks();

    public static int[] quickSort(int[] array) {

        if (array == null) {
            return array;
        }

        int[] arr = new int[array.length];
        System.arraycopy(array, 0, arr, 0, array.length);
        quickSortStrategy.quickSort(arr, 0, arr.length - 1);
        return arr;
    }
}
