package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: <a href="https://leetcode.com/problems/roman-to-integer/">...</a>
 *
 * @author youyou
 * @date 3/4/21 8:03 PM
 */
public class Test13_Roman2Integer {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }

    private static class Solution {
        public int romanToInt(String s) {
            if (s == null || s.length() < 1) throw new UnsupportedOperationException();
            Map<Character, Integer> romanStringMap = new HashMap<Character, Integer>(){{
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }};
            
            return romanToInt(s, romanStringMap);
        }

        private int romanToInt(String s, Map<Character, Integer> romanStringMap) {
            char[] array = s.toCharArray();
            int result = 0;
            int i = 0;

            for (; i < array.length - 1; i++) {
                int left = romanStringMap.get(array[i]);
                int right = romanStringMap.get(array[i+1]);

                if (left < right) {
                    result += right - left;
                    i++;
                } else {
                    result += left;
                }
            }

            if (i < array.length) {
                result += romanStringMap.get(array[i]);
            }

            return result;
        }
    }
}
