package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: https://leetcode.com/problems/generate-parentheses/
 *
 * @author youyou
 * @date 3/18/21 4:02 PM
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(1));
        System.out.println(solution.generateParenthesis(2));
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(4));
    }

    private static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            generateParenthesis("", 0, 0, n, list);
            return list;
        }

        private void generateParenthesis(String current, int open, int close, int max, List<String> list) {
            if (current.length() == max * 2) {
                list.add(current);
                return;
            }

            if (open < max)
                generateParenthesis(current + "(", open + 1, close, max, list);
            if (close < open)
                generateParenthesis(current + ")", open, close + 1, max, list);
        }
    }
}
