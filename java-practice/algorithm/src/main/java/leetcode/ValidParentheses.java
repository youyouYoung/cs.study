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
