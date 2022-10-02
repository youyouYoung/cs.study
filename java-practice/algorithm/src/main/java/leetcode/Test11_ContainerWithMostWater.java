package leetcode;

/**
 * 功能描述: <a href="https://leetcode.com/problems/container-with-most-water/">...</a>
 *
 * @author youyou
 * @date 3/4/21 2:59 PM
 */
public class Test11_ContainerWithMostWater {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height));

        height = new int[]{1,1};
        System.out.println(solution.maxArea(height));

        height = new int[]{4,3,2,1,4};
        System.out.println(solution.maxArea(height));

        height = new int[]{1,2,1};
        System.out.println(solution.maxArea(height));
    }

    private static class Solution {
        public int maxArea(int[] height) {
            if (height == null || height.length < 2) return 0;

            return controlVariableWidth(height);
        }

        /**
         * Description: list all possible results and comparing, then get the max area
         *
         * @param height the height array of each line
         * @return the max area
         * @author youyou
         * @date 3/4/21 3:21 PM
         */
        private int exhaustion(int[] height) {
            int length = height.length;
            int maxArea = 0;

            for (int left = 0; left < length; left++) {
                for (int right = left + 1; right < length; right++) {
                    int width = right - left;
                    int minHeight = Math.min(height[left], height[right]);
                    int area = width * minHeight;

                    maxArea = Math.max(maxArea, area);
                }
            }

            return maxArea;
        }

        /**
         * Description: In this case, we have two variables: width and height. and area = width * height
         * So we let width decrease 1 every time from max width(the length of array) to min width(1).
         *
         * At beginning: let l = 0, r = height.length - 1
         * So, we get: w = r - l, h = min(height[l], height[r]), a = w * h.
         * Next, we need to decrease the width by 1.
         * If we know height[l] > height[r] and let l += 1:
         * Then, we get the new w is smaller than the old one.
         * and the max value of min(height[l], height[r]) is height[r], because height[r] is the min value of last step.
         * So, the new a is smaller than the last a.
         *
         * So, If we know height[l] > height[r], and the right operation is let r -= 1.
         *
         *
         * @param height the height array of each line
         * @return the max area
         * @author youyou
         * @date 3/4/21 3:53 PM
         */
        private int controlVariableWidth(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;

            while (left < right) {
                int width = right - left;
                int minHeight = Math.min(height[left], height[right]);
                int area = width * minHeight;

                maxArea = Math.max(maxArea, area);
                if (height[left] == minHeight) left++;
                else right--;
            }

            return maxArea;
        }
    }
}
