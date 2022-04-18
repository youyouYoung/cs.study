package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description: https://leetcode.com/problems/valid-parentheses/
 *
 * @author youyou
 * @date 3/12/21 4:13 PM
 */
public class ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
    }

    private static class Solution {
        /**
         * Description: determine if the input string is valid.
         *
         * An input string is valid if:
         *  1. Open brackets must be closed by the same type of brackets.
         *  2. Open brackets must be closed in the correct order.
         *
         *
         * First, Splitting the brackets to two groups, which are open brackets and close brackets.
         *
         * Then, using a stack to hold every char in the string.
         * if the current char is a open bracket, push it into the stack.
         * if the current char is a close bracket, pop the top char of the stack and then test if
         * the current char is a pair with the top char.
         *
         * if they are a pair, continue to the next char of the string. if not,
         * return false.
         *
         * @param s the input string
         * @return is the string valid
         * @author youyou
         * @date 3/18/21 10:10 AM
         */
        public boolean isValid(String s) {
            if (s == null || s.length() % 2 != 0) return false;
            Map<Character, Character> map = new HashMap<Character, Character>(9){{
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }};
            Stack<Character> stack = new Stack<>();

            char[] array = s.toCharArray();
            for (char c : array) {
                if (map.containsKey(c)) {
                    char value = map.get(c);
                    if (stack.isEmpty() || stack.pop() != value) return false;
                } else stack.push(c);
            }

            return stack.isEmpty();
        }
    }
}
