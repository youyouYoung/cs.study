package leetcode;

import java.util.Arrays;

/**
 * Description: <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">...</a>
 *
 * @author youyou
 * @date 10/1/22 2:02 PM
 */
public class Test34_TheFirstAndLastPositionOfElement {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] {1, 2};
        int target = 2;
        System.out.println(Arrays.toString(solution.searchRange(nums, target)));
    }

    private static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[] {-1, -1};
            way2(result, nums, target);
            return result;
        }

        /**
         * iteration - from the left to right
         * */
        private void way1(int[] result, int[] nums, int target) {
            int index = 0;
            boolean searchLast = false;

            while (index < nums.length) {
                if (nums[index] == target) {
                    if (!searchLast) {
                        searchLast = true;
                        result[0] = index;
                    }
                    result[1] = index;
                } else if (searchLast) {
                    break;
                }
                index++;
            }
        }

        /**
         * binary search - drop half elements if target is not in there.
         * nums = [5,7,7,8,8,10], target = 8
         * 1. find the middle position: middleIndex = 2
         * 2. if nums[middleIndex] < target, drop left subarray.
         *    2.1 update the start index of nums as middleIndex + 1
         *    2.2 continue loop
         * 3. if nums[middleIndex] > target, drop right subarray.
         *    3.1 update the end index of nums as middle - 1
         *    3.2 continue loop
         * 4. if nums[middleIndex] == target, end loop and set two pointers: left, right.
         *    4.1 make the left move forward until nums[middleIndex] != target or less than 0.
         *    4.2 make the right move backward until nums[middleIndex] != target or bigger than nums.length() - 1.
         *    4.3 then the result is [left + 1, right - 1]
         * */
        private void way2(int[] result, int[] nums, int target) {
            if (nums.length < 1) return;

            int start = 0;
            int end = nums.length - 1;
            int middleIndex = start;
            while (start <= end) {
                middleIndex = (start + end) / 2;
                if (nums[middleIndex] < target)
                    start = middleIndex + 1;
                else if (nums[middleIndex] > target)
                    end = middleIndex - 1;
                else break;
            }

            if (nums[middleIndex] != target) return;
            int left = middleIndex - 1;
            int right = middleIndex + 1;
            while (left > -1 || right < nums.length) {
                if (left > -1 && nums[left] == target) left--;
                if (right < nums.length && nums[right] == target) right++;

                if ((left < 0 || nums[left] != target) && (right >= nums.length || nums[right] != target)) break;
            }
            result[0] = left + 1;
            result[1] = right - 1;
        }
    }
}
