package leetcode;

/**
 * 功能描述: https://leetcode.com/problems/reverse-integer/
 *
 * @author youyou
 * @date 3/15/20 10:38 PM
 */
public class ReverseInteger_test7 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reverse(1534236469));
        System.out.println(s.reverse(-123));
        System.out.println(s.reverse(120));

    }

    private static class Solution {
        public int reverse(int x) {
            int max = 214748364;  //7
            int min = -214748364; //8

            int count = 0;
            int y = 0;
            while (x != 0) {
                if (count == 9) {
                    if (y > max || y < min) return 0;
                    if ((y == max && x % 10 > 7) || (y == min && x % 10 < -8)) return 0;
                }
                y = y * 10 + x % 10;
                x = x / 10;

                count++;
            }

            return y;
        }
    }
}
