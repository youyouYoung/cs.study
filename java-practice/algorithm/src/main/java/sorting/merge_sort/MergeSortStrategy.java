package sorting.merge_sort;

/**
 * 功能描述: 归并排序算法的实现策略
 *
 * @author youyou
 * @date 11/10/19 9:13 PM
 */
interface MergeSortStrategy {

    /**
     * 功能描述: 对数组使用归并排序算法进行排序
     *
     * @param array 待排序数组
     * @param length 数组真实长度
     * @param beginIndex 排序的起始位置
     * @param endIndex 排序的截止位置
     * @author youyou
     * @date 11/10/19 9:22 PM
     */
    void mergeSort(int[] array, int length, int beginIndex, int endIndex);
}
