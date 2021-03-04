package sorting.merge_sort;

/**
 * 功能描述: 归并排序的实现
 *
 * @author youyou
 * @date 11/10/19 9:23 PM
 */
class MergeSortStrategyByMyself implements MergeSortStrategy {

    public void mergeSort(int[] array, int length, int beginIndex, int endIndex) {

        if (array == null || beginIndex < 0 || beginIndex > endIndex || endIndex > length) {
            throw new UnsupportedOperationException();
        }

        if (beginIndex == endIndex) {
            return;
        }

        int middleIndex = beginIndex + ((endIndex - beginIndex) >> 1);
        if (middleIndex > beginIndex) {
            mergeSort(array, length, beginIndex, middleIndex);
        }
        if (middleIndex < endIndex) {
            mergeSort(array, length, middleIndex + 1, endIndex);
        }

        merge(array, beginIndex, middleIndex, middleIndex + 1, endIndex);
    }

    private void merge(int[] array, int beginIndex1, int endIndex1, int beginIndex2, int endIndex2) {

        if (array == null || beginIndex1 > endIndex1 || beginIndex2 > endIndex2 || endIndex1 > array.length || endIndex2 > array.length || beginIndex1 < 0 || beginIndex2 < 0) {
            throw new UnsupportedOperationException();
        }

        int index = 0;
        int start = beginIndex1 > beginIndex2 ? beginIndex2 : beginIndex1;
        int[] arrTemp = new int[endIndex1 - beginIndex1 + endIndex2 - beginIndex2 + 2];
        while (beginIndex1 <= endIndex1 && beginIndex2 <= endIndex2) {
            arrTemp[index++] = array[beginIndex1] > array[beginIndex2] ? array[beginIndex2++] : array[beginIndex1++];
        }

        while (beginIndex1 <= endIndex1) {
            arrTemp[index++] = array[beginIndex1++];
        }

        while (beginIndex2 <= endIndex2) {
            arrTemp[index++] = array[beginIndex2++];
        }


        for (int i : arrTemp) {
            array[start++] = i;
        }
    }
}
