package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a>
 *
 * @author youyou
 * @date 2/15/22 2:18 PM
 */
public class LongestSubstring_test3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLongestSubstring("abcabcbb");
    }

    private static class Solution {
        public int lengthOfLongestSubstring(String s) {
//         if (s == null || "".equals(s))
//             return 0;

//         char[] array = s.toCharArray();
//         int bi, ei, max, length;
//         bi = ei = 0;
//         max = length = 1;
//         Map<Character, Integer> map = new HashMap<>(26 << 2);

//         for (; ei < array.length - 1; ei++) {
//             map.put(array[ei], ei);

//             if (!map.containsKey(array[ei+1])) {
//                 length++;
//                 max = max > length ? max : length;
//             } else {
//                 int index = map.get(array[ei+1]);
//                 length = length - (index + 1 - bi) + 1;
//                 for (; bi < index + 1; bi++) {
//                     map.remove(array[bi]);
//                 }
//             }
//         }

//         return max;

            if (s == null || "".equals(s)) return 0;

            int max = 0;
            int l = s.length();
            Map<Character, Integer> map = new HashMap<>(26 * 2 << 2);

            for (int b = -1, e = 0; e < l; e++) {
                char c = s.charAt(e);
                if (map.containsKey(c)) {
                    b = Math.max(map.get(c), b); // 因为没有清除不在这个窗口内的字符位置. 所以b != map.get(array[e]) + 1
                }

                int length = e - b;
                max = Math.max(max, length);
                map.put(c, e);
            }

            return max;
        }
    }
}


