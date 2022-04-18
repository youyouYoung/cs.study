package sorting.merge_sort;

/**
 * 功能描述: 归并排序的驱动类
 *
 * @author youyou
 * @date 11/10/19 9:47 PM
 */
public class MergeSortDriver {

    private static MergeSortStrategy mergeSortStrategy = new MergeSortStrategyByMyself();

    public static int[] mergeSort(int[] array) {

        if (array == null) {
            return array;
        }

        int[] arr = new int[array.length];
        System.arraycopy(array, 0, arr, 0, array.length);
        mergeSortStrategy.mergeSort(arr, arr.length, 0, arr.length - 1);
        return arr;
    }
}
