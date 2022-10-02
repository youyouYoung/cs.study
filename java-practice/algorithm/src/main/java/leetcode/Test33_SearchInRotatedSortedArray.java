package leetcode;

/**
 * Description: <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">...</a>
 *
 * @author youyou
 * @date 9/30/22 4:02 PM
 */
public class Test33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 3};
        Solution solution = new Solution();
        System.out.println(solution.search(nums, 3));
    }

    private static class Solution {
        public int search(int[] nums, int target) {
            return way3(nums, target);
        }

        public int way1(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) return i;
            }

            return -1;
        }

        public int way2(int[] nums, int target) {
            boolean foundRotatedPoint = false;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) return i;

                if (!foundRotatedPoint) foundRotatedPoint = i > 0 && nums[i] < nums[i - 1];
                if (foundRotatedPoint && nums[i] > target) return -1;
            }

            return -1;
        }

        /**
         * 5 -> 3 4 5 6 0 1 2  target bigger than element at index 0, the answer could be at left
         *    get middle element 6
         *    bigger than target, using dichotomy to find it at left part ( - set as an independent method)
         *    what if it less than target: (we should take care of the rotated position)
         *        find the rotated position ( - set as an independent method)
         *        search the position from middle element to the end element of the left part
         *
         * 1 -> 3 4 5 6 0 1 2  target less than element at index 0, the answer could be at right
         *     find the rotated position ( - use above method)
         *     search the target at the right part.
         * */
        public int way3(int[] nums, int target) {
            int rotated = rotatedIndex(nums);
            System.out.println(rotated);
            if (target >= nums[0]) {
                return dichotomy(nums, 0, rotated == 0 ? nums.length - 1 : rotated - 1, target);
            } else {
                return dichotomy(nums, rotated, nums.length - 1, target);
            }
        }

        // todo this is a difficult part for me
        private int dichotomy(int[] nums, int left, int right, int target) {
            while (left < right) {
                int middle = (left + right) / 2;
                if (target == nums[middle]) return middle;

                if (target < nums[middle]) right = middle - 1;
                else left = middle + 1;
            }
            return nums[left] == target ? left : -1;
        }

        // todo this is a difficult part for me
        private int rotatedIndex(int[] nums) {
            if (nums[0] < nums[nums.length - 1]) return 0;

            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int middle = (left + right) / 2;
                if (middle > 0 && middle < nums.length - 1) {
                    if (nums[middle] < nums[middle - 1]) return middle;
                    else if (nums[middle] > nums[middle + 1]) return middle + 1;
                } else {
                    return nums[left] > nums[right] ? right : left;
                }

                if (nums[middle] > nums[left]) left = middle;
                else right = middle;
            }
            return left;
        }
    }
}
