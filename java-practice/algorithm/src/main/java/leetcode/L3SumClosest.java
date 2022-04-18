package leetcode;

import java.util.Arrays;

/**
 * Description: https://leetcode.com/problems/3sum-closest/
 *
 * @author youyou
 * @date 3/6/21 5:58 PM
 */
public class L3SumClosest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    private static class Solution {
        /**
         * Description: find three integers in nums such that the sum is closest to target.
         *
         * In order to find the closest sum, we sort the nums first so that we could find the direction
         * where we can get numbers we need in the array.
         *
         * Then, we take one of elements named x from the array and suppose it is the number which add with two others
         * and the sum is the closest one. let's take a number from the beginning as the x.
         *
         * After above, we need to choose two others from the left array, and we know that these two numbers' sum is target-x.
         * so we choose numbers from two sides of the left array separately.
         *
         * Then we get two numbers named y and z and then calculate x+y+z. if this sum is smaller than target then move forward of the left index.
         * else if this sum is bigger than target then move behind of the right index, but if the sum is just equals target,
         * that is our result.
         *
         * also we store the sum as a candidate, if the next sum is closer than this one just update the candidate.
         *
         * @param nums integer array
         * @param target the target
         * @return the closest sum
         * @author youyou
         * @date 3/6/21 7:13 PM
         */
        public int threeSumClosest(int[] nums, int target) {
            if (nums == null || nums.length < 3) throw new UnsupportedOperationException();
            Arrays.sort(nums);

            int closestSum = target;
            for (int i = 0; i < nums.length; i++) {
                int x = nums[i];
                int left = i+1;
                int right = nums.length - 1;
                if (i == 0) closestSum = nums[left] + nums[right] + x;
                else if (x == nums[i - 1]) continue;

                while (left < right) {
                    int sum = nums[left] + nums[right] + x;
                    if (sum == target) return target;

                    if (Math.abs(target - closestSum) > Math.abs(target - sum)) {
                        closestSum = sum;
                    }

                    boolean moveLeft = false;
                    boolean moveRight = false;
                    if (sum < target) moveLeft = true;
                    else moveRight = true;

                    while (moveLeft && left++ < right && nums[left] == nums[left - 1]);
                    while (moveRight && right-- > left && nums[right] == nums[right + 1]);
                }
            }

            return closestSum;
        }
    }
}
