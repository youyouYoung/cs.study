package sorting.quick_sort;

import java.util.Random;

/**
 * 功能描述: 从剑指offer中看到的排序方法
 *
 * @author youyou
 * @date 11/10/19 7:37 PM
 */
class QuickSortStrategyByBooks implements QuickSortStrategy {

    private static void swap(int[] arr, int index1, int index2) {

        if (arr == null || index1 < 0 || index1 >= arr.length || index2 < 0 || index2 >= arr.length) {
            return;
        }

        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * 功能描述: 将数组分为两个区域, 前面的部分都小于一个比较值, 后面的部分大于等于这个比较值. 并返回比较值的下标位置
     *
     * @param arr 带分区数组
     * @param length 数组需要分区的范围
     * @param start 数组分区的开始位置
     * @param end 数组分区的结束为止
     * @return 用于比较的分水岭值的最终存放位置
     * @author youyou
     * @date 11/10/19 8:20 PM
     */
    public int partition(int[] arr, int length, int start, int end) {

        if (arr == null || start < 0 || start > end || end >= length){
            throw new UnsupportedOperationException();
        }

        Random random = new Random();
        // anchor -> 当前遍历到的数组下标
        int anchor;
        // 确定比较值的位置
        anchor = random.nextInt(length);
        // 交换比较值与最后一个值的位置
        swap(arr, anchor, end);

        // lastSmallIndex -> 最后一个小于比较值的下标
        int lastSmallIndex = start - 1;
        // 遍历到最后一个值之前截止
        for (anchor = start; anchor < end; anchor++) {
            // 当发现有元素小于比较值时
            if (arr[anchor] < arr[end]) {
                // 为小于比较值的元素确定存放位置
                lastSmallIndex++;
                // 确定的存放位置不等于该元素现有位置, 则交换
                if (lastSmallIndex != anchor) {
                    swap(arr, lastSmallIndex, anchor);
                }
            }
        }

        // 为比较值确定存放位置
        lastSmallIndex++;
        // 将比较值存放到中间
        swap(arr, lastSmallIndex, end);
        return lastSmallIndex;
    }

    public void quickSort(int[] arr, int beginIndex, int endIndex) {

        if (arr == null || beginIndex < 0 || beginIndex > endIndex || endIndex >= arr.length) {
            throw new UnsupportedOperationException();
        }

        if (beginIndex == endIndex) {
            return;
        }

        int position = partition(arr, arr.length, beginIndex, endIndex);

        if (position > beginIndex) {
            quickSort(arr, beginIndex, position - 1);
        }

        if (position < endIndex) {
            quickSort(arr, position + 1, endIndex);
        }
    }
}
