package leetcode;

/**
 * 功能描述: <a href="https://leetcode.com/problems/zigzag-conversion/">...</a>
 *
 * @author youyou
 * @date 3/15/20 8:12 PM
 */
public class Test6_ZigZagConversion {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
        System.out.println("PAHNAPLSIIGYIR".equals(new Solution().convert("PAYPALISHIRING", 3)));

        System.out.println(new Solution().convert("PAYPALISHIRING", 4));
        System.out.println("PINALSIGYAHRPI".equals(new Solution().convert("PAYPALISHIRING", 4)));
    }

    private static class Solution {
        public String convert(String s, int numRows) {
            if (numRows < 2) return s;

            int length = s.length();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < numRows && i < length; i++) {
                int loopCount = 1;
                while (true) {
                    int index = i + (loopCount - 1) * (2 * numRows - 2);
                    if (index >= length) break;
                    builder.append(s.charAt(index));

                    if (i != 0 && i != numRows - 1) {
                        index += 2 * (numRows - i - 1);
                        if (index >= length) break;
                        builder.append(s.charAt(index));
                    }

                    loopCount++;
                }
            }
            return builder.toString();
        }
    }
}
