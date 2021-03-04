package quick_sort;

import sorting.quick_sort.QuickSortDriver;

import java.util.Arrays;

/**
 * 功能描述: TODO
 *
 * @author youyou
 * @date 11/10/19 8:54 PM
 */
public class QuickSortTest {


    public static void main(String[] args) {

        int[] array = {5, 1, 2, 4, 5, 6, 3, 9};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(QuickSortDriver.quickSort(array)));
        System.out.println(QuickSortDriver.quickSort(null));
    }
}
