package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: https://leetcode.com/problems/integer-to-roman/
 *
 * @author youyou
 * @date 3/4/21 4:24 PM
 */
public class Integer2Roman_test12 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(3));
        System.out.println(solution.intToRoman(4));
        System.out.println(solution.intToRoman(9));
        System.out.println(solution.intToRoman(58));
        System.out.println(solution.intToRoman(1994));
        // solution.closestSmallValue(4, new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000});
    }

    private static class Solution {
        public String intToRoman(int num) {
            if (num < 1) throw new UnsupportedOperationException();

            int[] romanNumbers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
            Map<Integer, String> romanStringMap = new HashMap<Integer, String>(){{
               put(1, "I");
               put(4, "IV");
               put(5, "V");
               put(9, "IX");
               put(10, "X");
               put(40, "XL");
               put(50, "L");
               put(90, "XC");
               put(100, "C");
               put(400, "CD");
               put(500, "D");
               put(900, "CM");
               put(1000, "M");
            }};

            return intToRoman(num, romanNumbers, romanStringMap);
        }

        private String intToRoman(int num, int[] romanNumbers, Map<Integer, String> romanStringMap) {
            StringBuilder result = new StringBuilder();
            while (num > 0) {
                int closestNumber = closestSmallValue(num, romanNumbers);
                int repeatTimes = num / closestNumber;
                num = num % closestNumber;

                for (int i = 0; i < repeatTimes; i++) {
                    result.append(romanStringMap.get(closestNumber));
                }
            }

            return result.toString();
        }

        /**
         * Description: find the closest item of the @param{number} in the @param{array}.
         *
         * @param number target number
         * @param array number array
         * @return the closest number to the target number.
         * @author youyou
         * @date 3/4/21 4:46 PM
         */
        private int closestSmallValue(int number, int[] array) {
            int left = 0;
            int right = array.length - 1;

            while (right - left > 1) {
                int middle = (left + right) / 2;

                if (array[middle] == number) return number;
                if (array[middle] < number) left = middle;
                else right = middle - 1;
            }

            return array[right] > number ? array[left] : array[right];
        }
    }
}
