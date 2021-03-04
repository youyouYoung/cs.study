package string.driver;

import static string.StringUtils.stringToInt;

/**
 * 功能描述: string包的驱动类
 *
 * @author youyou
 * @date 12/2/19 9:59 PM
 */
public class StringDriver {

    public static void main(String[] args) {
        System.out.println(stringToInt("12300"));
        System.out.println(stringToInt(""));
        System.out.println(stringToInt(null));
        System.out.println(Integer.parseInt("123"));
    }
}
