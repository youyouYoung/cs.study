package leetcode;

/**
 * 功能描述: <a href="https://leetcode.com/problems/palindrome-number/">...</a>
 *
 * @author youyou
 * @date 3/18/20 9:55 PM
 */
public class Test9_PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isPalindrome(1001));
        System.out.println(solution.isPalindrome(1000000001));
        System.out.println(solution.isPalindrome(-1221));
        System.out.println(solution.isPalindrome(-1));
        System.out.println(solution.isPalindrome(9));
        // System.out.println(99/100);
    }

    private static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) return false;
            if (x < 10) return true;

            int halfNumber = 0;
            for (int left = x / 100, mod = x % 100; mod > 0 || left > 0; mod = left % 100, left /= 100) {
                halfNumber = halfNumber * 10 + x % 10;
                if (left > 0 || mod > 9) x /= 10;
            }

            return x == halfNumber;
        }
    }
}
