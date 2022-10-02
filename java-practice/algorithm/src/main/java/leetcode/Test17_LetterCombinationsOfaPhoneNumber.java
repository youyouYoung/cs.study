package leetcode;

import java.util.*;

/**
 * Description: <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/">...</a>
 *
 * @author youyou
 * @date 3/11/21 1:33 PM
 */
public class Test17_LetterCombinationsOfaPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("2"));
    }

    private static class Solution {
        // the map about number and letters which the number represent.
        private static Map<Character, char[]> numberLettersMap = new HashMap<Character, char[]>() {{
            put('2', "abc".toCharArray());
            put('3', "def".toCharArray());
            put('4', "ghi".toCharArray());
            put('5', "jkl".toCharArray());
            put('6', "mno".toCharArray());
            put('7', "pqrs".toCharArray());
            put('8', "tuv".toCharArray());
            put('9', "wxyz".toCharArray());
        }};

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() < 1) return new ArrayList<>();

            // initiate the result list with fixed length.
            List<String> result = new ArrayList<>((int)Math.pow(3, digits.length()));
            char[] numbers = digits.toCharArray();
            fillLetterList(numbers, 0, new StringBuilder(), result);
            return result;
        }

        /**
         * Description: use the depth-first thought.
         *
         * First, take one of the numbers from the front of the digits. and get letters it represents.
         * looping the letters and every time append one of them into a string.
         *
         * Do above steps until going to the end of digits string then we get a string which is one of target strings.
         * putting it into the result list.
         *
         * @param numbers inputted digit numbers.
         * @param index the current loop index of the numbers array.
         * @param beforeLetter the combination string created by before numbers
         * @param result the result string list.
         * @author youyou
         * @date 3/11/21 2:15 PM
         */
        private void fillLetterList(char[] numbers, int index, StringBuilder beforeLetter, List<String> result) {
            if (index >= numbers.length) {
                result.add(beforeLetter.toString());
                return;
            }

            char[] letters = numberLettersMap.get(numbers[index]);
            for (char letter : letters) {
                beforeLetter.append(letter);
                fillLetterList(numbers, index+1, beforeLetter, result);
                beforeLetter.deleteCharAt(beforeLetter.length() - 1);
            }
        }
    }
}
