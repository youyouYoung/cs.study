package leetcode;

import java.util.Arrays;

/**
 * Description: https://leetcode.com/problems/next-permutation/
 * date 20/04/2022 14:18
 *
 * @author yangguang
 */
public class NextPermutation_test31 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        Solution solution = new Solution();
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static class Solution {
        public void nextPermutation(int[] nums) {
            resolving_2(nums);
        }

        private void resolving_2(int[] nums) {
            int i = nums.length - 1;

            while (i - 1 >= 0) {
                if (nums[i - 1] < nums[i]) break;
                i--;
            }

            Arrays.sort(nums, i, nums.length);
            for (int j = i; j < nums.length && i - 1 >= 0; j++) {
                if (nums[j] > nums[i - 1] || j == nums.length - 1) {
                    int temp = nums[i - 1];
                    nums[i - 1] = nums[j];
                    nums[j] = temp;
                    break;
                }
            }
        }

        /**
         * description 把最低位置的值与比他小且靠它最近的值互换, 然后再将换到的位置之后的数值按从小到大排序.
         * 如果最低位置的值是最小的, 最低位置前移一位
         * date 15:31 2022/4/20
         *
         * @author yangguang
         **/
        private void resolving_1(int[] nums) {
            for (int right = nums.length - 1; right >= 0; right--) {
                for (int i = right - 1; i >= 0; i--) {
                    if (nums[right] > nums[i]) {
                        int temp = nums[right];
                        nums[right] = nums[i];
                        nums[i] = temp;
                        Arrays.sort(nums, i + 1, nums.length);
                        return;
                    }
                }
            }

            int right = nums.length -1;
            int left = 0;
            while (right > left) {
                int temp = nums[right];
                nums[right--] = nums[left];
                nums[left++] = temp;
            }
        }
    }
}
