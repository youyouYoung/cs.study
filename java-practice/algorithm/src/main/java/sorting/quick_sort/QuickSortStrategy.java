package sorting.quick_sort;

/**
 * 功能描述: 快排策略
 *
 * @author youyou
 * @date 11/10/19 8:36 PM
 */
interface QuickSortStrategy {

    /**
     * 功能描述: 将数组分为两个区域, 前面的部分都小于一个比较值, 后面的部分大于等于这个比较值. 并返回比较值的下标位置
     *
     * @param arr 带分区数组
     * @param length 数组需要分区的范围
     * @param beginIndex 数组分区的开始位置
     * @param endIndex 数组分区的结束为止
     * @return 用于比较的分水岭值的最终存放位置
     * @author youyou
     * @date 11/10/19 8:20 PM
     */
    int partition(int[] arr, int length, int beginIndex, int endIndex);

    /**
     * 功能描述: 对传入数组使用快排算法排序
     *
     * @param arr 传入数组
     * @param beginIndex 数组需要排序的起始下标位置
     * @param endIndex 数组需要排序的截止下标位置
     * @author youyou
     * @date 11/10/19 8:38 PM
     */
    void quickSort(int[] arr, int beginIndex, int endIndex);
}
