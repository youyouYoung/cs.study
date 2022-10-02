package leetcode;

import java.util.Arrays;

/**
 * Description: <a href="https://leetcode.com/problems/remove-element/">...</a>
 *
 * @author youyou
 * @date 3/19/21 10:26 AM
 */
public class Test27_RemoveElement {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{3,2,2,3};
        System.out.println(solution.removeElement(nums, 3));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,1,2,2,3,0,4,2};
        System.out.println(solution.removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));
    }

    private static class Solution {
        /**
         * Description: remove all elements which equals val from the nums, and return the new length.
         *
         * First, holding a index (emptyPosition) indicate at the position before which the elements do not equal the val.
         *
         * Then, use another index (i) to loop the array from the beginning to the end. if we found the current number is equals
         * val and the emptyPosition is not initiated, set the emptyPosition as the current index i.
         *
         * if we found the current number do not equal val and the emptyPosition has value. move the current number to the
         * position of emptyPosition, and then emptyPosition move on.
         *
         * Move the index until to the end. then return the emptyPosition.
         *
         * @param nums the array of numbers
         * @param val value
         * @return the new length of array
         * @author youyou
         * @date 3/19/21 10:40 AM
         */
        public int removeElement(int[] nums, int val) {
            if (nums == null) return 0;

            int emptyPosition = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == val && emptyPosition < 0) emptyPosition = i;

                if (nums[i] != val && emptyPosition > -1) nums[emptyPosition++] = nums[i];
            }
            return emptyPosition < 0 ? nums.length : emptyPosition;
        }
    }
}
