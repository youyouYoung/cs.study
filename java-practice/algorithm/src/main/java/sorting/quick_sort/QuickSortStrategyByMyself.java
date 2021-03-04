package sorting.quick_sort;

/**
 * 功能描述: 快排算法
 *
 * @author youyou
 * @date 11/10/19 6:35 PM
 */
class QuickSortStrategyByMyself implements QuickSortStrategy {

    public int partition(int[] array, int length, int beginIndex, int endIndex) {

        if (array == null) {
            throw new NullPointerException();
        }

        if (beginIndex >= endIndex || beginIndex < 0) {
            throw new UnsupportedOperationException();
        }

        int anchor = array[beginIndex];
        int left = beginIndex;
        int right = endIndex;
        for(; left < right; right--) {
            if (array[right] < anchor) {
                array[left] = array[right];

                for (; left < right; left++) {
                    if (array[left] > anchor) {
                        array[right] = array[left];
                        break;
                    }
                }
            }
        }
        array[left] = anchor;
        return left;
    }

    public void quickSort(int[] array, int beginIndex, int endIndex) {

        if (array == null || beginIndex >= endIndex || beginIndex < 0) {
            return;
        }

        int positionOfAnchor = partition(array, array.length, beginIndex, endIndex);
        quickSort(array, beginIndex, positionOfAnchor - 1);
        quickSort(array, positionOfAnchor + 1, endIndex);
    }
}
