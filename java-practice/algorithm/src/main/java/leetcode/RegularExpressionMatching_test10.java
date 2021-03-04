package leetcode;

import java.util.Objects;

/**
 * 功能描述: https://leetcode.com/problems/regular-expression-matching/
 *
 * @author youyou
 * @date 4/2/20 8:51 PM
 */
public class RegularExpressionMatching_test10 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aa", "a"));
        System.out.println(solution.isMatch("aa", "a*"));
        System.out.println(solution.isMatch("ab", ".*"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
        System.out.println(solution.isMatch("mississippi", "mis*is*ip*."));
    }

    private static class Solution {
        public boolean isMatch(String s, String p) {
            if (s == null || s.length() == 0) {
                return ".*".equals(p);
            }

            char[] sArray = s.toCharArray();
            char[] pArray = p.toCharArray();
            Character lastLetter = null;
            int i = 0;

            // i 每次循环+1 有bug
            // asdfffgh asdf*fgh
            for (int j = 0; i < sArray.length && j < pArray.length; i++) {
                char x = sArray[i];
                char y = pArray[j];

                if (y >= 'a' && y<= 'z') {
                    lastLetter = y;
                    if (x == y) {
                        j++;
                    } else {
                        if (j < pArray.length - 1 && pArray[j+1] == '*') {
                            j += 2;
                            lastLetter = null;
                        } else {
                            return false;
                        }
                    }
                } else if (y == '.') {
                    lastLetter = y;
                    j++;
                } else {
                    if (Objects.isNull(lastLetter)) {
                        return false;
                    } else if (lastLetter >= 'a' && lastLetter <= 'z') {
                        if (x != lastLetter) {
                            lastLetter = null;
                            j++;
                        }
                    } else {
                        return true;
                    }
                }
            }

            return i == sArray.length;
        }
    }
}
