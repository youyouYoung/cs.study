package quick_sort;

import sorting.merge_sort.MergeSortDriver;

import java.util.Arrays;

/**
 * 功能描述: 归并排序测试
 *
 * @author youyou
 * @date 11/10/19 8:54 PM
 */
public class MergeSortTest {


    public static void main(String[] args) {

        int[] array = {5, 1, 2, 4, 5, 6, 3, 9};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(MergeSortDriver.mergeSort(array)));
        System.out.println(MergeSortDriver.mergeSort(null));
    }

    /**
     * 功能描述: 测试char是否可以转为int使用
     *
     * @author youyou
     * @date 12/2/19 9:43 PM
     */
    static void charTest() {
        System.out.println(1%2);
        int[] a = new int[1022];
        a['c']=1;
    }
}
