package leetcode;

/**
 * 功能描述: <a href="https://leetcode.com/problems/string-to-integer-atoi/">...</a>
 *
 * @author youyou
 * @date 3/16/20 10:59 PM
 */
public class Test8_String2Integer {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE / 10);
        System.out.println(Integer.MIN_VALUE / 10);
        System.out.println(Integer.MIN_VALUE % 10);
        System.out.println(Integer.MAX_VALUE % 10);

        Solution solution = new Solution();
        System.out.println(solution.myAtoi("-2147483647"));
        System.out.println(solution.myAtoi("   -42"));
        System.out.println(solution.myAtoi("4193 with words"));
        System.out.println(solution.myAtoi("words and 987"));
        System.out.println(solution.myAtoi("-91283472332"));
    }

    private static class Solution {
        public int myAtoi(String str) {
            if (str == null || "".equals(str)) return 0;

            int result = 0;
            char[] array = str.toCharArray();
            boolean isPositive = true;
            boolean mustNumerical = false;
            int maxEdge = Integer.MAX_VALUE / 10;
            int maxValueSuffix = Integer.MAX_VALUE % 10;
            int minValueSuffix = (Integer.MIN_VALUE % 10) * -1;
             

            for (int i = 0; i < array.length; i++) {
                if (array[i] != ' ' && array[i] != '-' && array[i] != '+' && (array[i] < '0' || array[i] > '9')) break;
                if (mustNumerical && (array[i] < '0' || array[i] > '9')) break;
                if (array[i] == ' ') continue;
                if (array[i] == '-' || array[i] == '+') {
                    isPositive = array[i] == '+';
                    mustNumerical = true;
                    continue;
                }
                mustNumerical = true;

                int n = array[i] - '0';
                if (result > maxEdge || (result == maxEdge && (isPositive ? n > maxValueSuffix : n > minValueSuffix))) return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                result = result * 10 + n;
            }

            return result * (isPositive ? 1 : -1);
        }
    }
}
