package string;

/**
 * 功能描述: 字符串转数字
 *
 * @author youyou
 * @date 12/2/19 9:00 PM
 */
public class StringUtils {

    /**
     * 功能描述: 字符串转成数字.
     *
     * 问题: 没有考虑正负数,大数问题
     *
     * @param s 字符串
     * @return 数字
     * @author youyou
     * @date 12/2/19 9:18 PM
     */
    public static int stringToInt(String s) {
        if (s == null || "".equals(s.trim())) {
            throw new RuntimeException("非法字符");
        }

        char[] array = s.toCharArray();
        int number = 0;
        for (char c : array) {
            if (c < '0' || c > '9') {
                throw new RuntimeException("非法字符");
            }
            number = number * 10 + c - '0';
        }
        return number;
    }
}
