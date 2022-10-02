package leetcode;

/**
 * Description: <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/">...</a>
 *
 * @author youyou
 * @date 3/19/21 11:03 AM
 */
public class Test28_FindTheIndexOfTheFirstOccurrence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr("hello", "ll"));
        System.out.println(solution.strStr("aaaaa", "bba"));
        System.out.println(solution.strStr("", ""));
    }

    private static class Solution {
        /**
         * Description: return the first occurrence of needle in haystack, -1 if the needle is not part of haystack, or 0 if the needle is "".
         *
         * Firstly, we loop the haystack and check every char in it to see if the char equals the first char of needle.
         *
         * if equals, get the substring of haystack from current index to check dose the substring equal with needle.
         *      if equals, return the current index.
         *      if not, continue loop.
         *
         * if the loop end and find the needle is not part of haystack then return -1.
         *
         * @param haystack the haystack
         * @param needle the needle
         * @return the index of haystack where needle is.
         * @author youyou
         * @date 3/19/21 11:24 AM
         */
        public int strStr(String haystack, String needle) {
            if (needle.length() == 0) return 0;

            char[] array = haystack.toCharArray();
            char target = needle.charAt(0);
            for (int i = 0; i < array.length; i++) {
                if (target == array[i] && equals(haystack, needle, i)) return i;
            }

            return -1;
        }

        private boolean equals(String haystack, String needle, int position) {
            if (haystack.length() - position < needle.length()) return false;

            String sub = haystack.substring(position, position + needle.length());
            return needle.equals(sub);
        }
    }
}
