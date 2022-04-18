package leetcode;

import java.util.Arrays;

/**
 * Description: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * @author youyou
 * @date 3/18/21 7:39 PM
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(solution.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    private static class Solution {
        /**
         * Description: remove the duplicated number from a sorted array and return the length of unduplicated array.
         *
         * We use to pointer to point the position in the array. the first pointer will index at the position
         * we prepare to move the unduplicated number into. and the other is index at each position of the array.
         *
         * @param nums the number array.
         * @return the length of new array.
         * @author youyou
         * @date 3/18/21 8:02 PM
         */
        public int removeDuplicates(int[] nums) {
            if (nums == null) return 0;
            if (nums.length < 2) return nums.length;

            int position2Fill = -1;
            int lastNumber = nums[0];
            for (int i = 1; i < nums.length; i++) {
                boolean isDuplicate = nums[i] == lastNumber;
                if (isDuplicate && position2Fill < 0) position2Fill = i;
                else if (!isDuplicate) {
                    lastNumber = nums[i];
                    if (position2Fill >= 0) {
                        nums[position2Fill] = nums[i];
                        position2Fill++;
                    }
                }
            }

            return position2Fill > -1 ? position2Fill: nums.length;
        }
    }
}
