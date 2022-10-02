package leetcode;

/**
 * Description: <a href="https://leetcode.com/problems/divide-two-integers/">...</a>
 *
 * @author youyou
 * @date 3/19/21 2:15 PM
 */
public class Test29_DivideTwoIntegers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide(10, 3));
        System.out.println(solution.divide(7, -3));
        System.out.println(solution.divide(0, 1));
        System.out.println(solution.divide(1, 1));
        System.out.println(solution.divide(-2147483648,-1));
        System.out.println(solution.divide(2147483647, 1));
        System.out.println(solution.divide(-2147483648, 2));
    }

    private static class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

            long x = Math.abs((long) dividend);
            long y = Math.abs((long) divisor);
            boolean isNegative = (dividend > 0) ^ (divisor > 0);

            int result = 0;
            while (x >= y) {
                int exponent = 1;
                while (x > (y << exponent) && (y << exponent) > 0) exponent++;

                exponent--;
                x -= y * (1 << exponent);
                result += 1 << exponent;
            }
            return isNegative ? -result : result;
        }
    }
}
