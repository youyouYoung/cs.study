package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: https://leetcode.com/problems/4sum/
 *
 * @author youyou
 * @date 3/11/21 2:56 PM
 */
public class L4Sum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println(solution.fourSum(new int[]{}, 0));
    }

    private static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 4) return result;

            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                int w = nums[i];
                if (i > 0 && nums[i - 1] == w) continue;
                if (w > target) break;

                for (int j = i + 1; j < nums.length; j++) {
                    int x = nums[j];
                    if (j > i + 1 && nums[j - 1] == x) continue;
                    if (w + x > target) break;

                    int left = j + 1;
                    int right = nums.length - 1;

                    while (left < right) {
                        int sum = w + x + nums[left] + nums[right];
                        boolean moveLeft = false;
                        boolean moveRight = false;

                        if (sum == target) {
                            result.add(Arrays.asList(w, x, nums[left], nums[right]));
                            moveLeft = moveRight = true;
                        } else if (sum < target) moveLeft = true;
                        else moveRight = true;

                        while (moveLeft && left++ < right && nums[left] == nums[left - 1]);
                        while (moveRight && left < right-- && nums[right] == nums[right + 1]);
                    }
                }
            }

            return result;
        }
    }
}
