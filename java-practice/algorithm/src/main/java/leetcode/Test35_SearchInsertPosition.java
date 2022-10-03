package leetcode;

/**
 * Description: <a url="https://leetcode.com/problems/search-insert-position/">leetcode link</a>
 *
 * @author youyou
 * @date 10/3/22 9:29 AM
 */
public class Test35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1,3,5,6};
        int target = 7;
        System.out.println(solution.searchInsert(nums, target));
    }

    private static class Solution {
        public int searchInsert(int[] nums, int target) {
            return way2(nums, target);
        }

        /**
         * Iteration - loop from left to the right until the right position
         */
        private int way1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target || nums[i] > target) return i;
            }
            return nums.length;
        }

        /**
         * Binary search - loop these steps below.
         *   1. calculate the middle position.
         *   2. compare the element at middle with the target.
         *     2.1 if equal, return the middle.
         *     2.2 if bigger, drop right part subarray, update the end position as middle - 1.
         *     2.3 if smaller, drop left part subarray, update the start position as middle + 1.
         *   3. until there is no element in subarray, end the loop.
         *   4. check the element of original array at middle position is equal with target.
         *     4.1 if equal, return middle.
         *     4.2 if bigger, return middle.
         *     4.3 if smaller, return middle + 1.
         * */
        private int way2(int[] nums, int target) {
            if (nums.length == 0) return 0;

            int start = 0;
            int end = nums.length - 1;
            int middle = 0;
            while (start <= end) {
                middle = (start + end) / 2;
                if (nums[middle] == target) return middle;
                if (nums[middle] > target) end = middle - 1;
                else start = middle + 1;
            }

            return nums[middle] >= target ? middle : middle + 1;
        }
    }
}
