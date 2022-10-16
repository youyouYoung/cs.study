package leetcode;

/**
 * Description: <a href="https://leetcode.com/problems/count-and-say/">leetcode problem 38</a>
 *
 * @author youyou
 * @date 10/16/22 11:14 AM
 */
public class Test38_CountAndSay {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(4));
    }

    private static class Solution {
        /**
         * This is a recursive formula.
         * 1. If `n == 1`, the result is "1".
         * 2. If `n != 1`, then
         *    1. Get the result of `countAndSay(n - 1)`.
         *    2. Say the above result.
         *       1. Define two variables: `number` and `countOfNumber`.
         *       2. Loop all chars in the result.
         *       3. If the current char equals the last, the `countOfNumber++`.
         *       4. Else saying the previous `number` and `countOfNumber`, then update these two variables.
         * 3. return the result.
         * */
        public String countAndSay(int n) {
            if (1 == n) return "1";

            String lastResult = countAndSay(n - 1);
            StringBuilder sBuilder = new StringBuilder();
            char number = ' ';
            int countOfNumber = 0;
            for (char letter : lastResult.toCharArray()) {
                if (number == letter) countOfNumber++;
                else {
                    if (countOfNumber != 0) {
                        sBuilder.append(countOfNumber);
                        sBuilder.append(number);
                    }
                    number = letter;
                    countOfNumber = 1;
                }
            }
            sBuilder.append(countOfNumber);
            sBuilder.append(number);
            return sBuilder.toString();
        }
    }
}
