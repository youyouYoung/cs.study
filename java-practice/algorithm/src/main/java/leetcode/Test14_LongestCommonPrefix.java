package leetcode;

/**
 * Description: <a href="https://leetcode.com/problems/longest-common-prefix/">...</a>
 *
 * @author youyou
 * @date 3/4/21 8:44 PM
 */
public class Test14_LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println("".substring(0, 0));
    }

    private static class Solution {
        /**
         * Description: find the longest common prefix of this string array
         *
         * Firstly, we suppose that the longest common prefix is the first string in the array, because
         * we know the true longest common prefix won't be longer than the first string.
         *
         * Then, we check if the first string is the common prefix of the second string in the array,
         * if not, cutting the last char of the first string and letting it be the new longest common prefix.
         * we try this process again and again until we find the longest common string between the first element
         * and the second. So this string could be the longest common prefix of the array.
         *
         * we use this string to match the left string in the array, just like the above process, until we
         * check every string in the array. we will get the longest common prefix.
         *
         * @param strings the string array
         * @return the longest common prefix
         * @author youyou
         * @date 3/4/21 8:46 PM
         */
        public String longestCommonPrefix(String[] strings) {
            if (strings == null || strings.length < 1) return "";

            String longestPrefix = strings[0];
            for (int i = 1; i < strings.length; i++) {
                String match = strings[i];

                if (longestPrefix.length() == 0) break;
                if (match.startsWith(longestPrefix)) continue;

                while (true) {
                    longestPrefix = longestPrefix.substring(0, Math.min(match.length(), longestPrefix.length()-1));

                    if (longestPrefix.length() == 0) break;
                    if (match.startsWith(longestPrefix)) break;
                }
            }

            return longestPrefix;
        }
    }
}
