package leetcode;

/**
 * 功能描述: https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author youyou
 * @date 3/9/20 9:44 PM
 */
public class LongestPalindrome_test5 {

    public static void main(String[] args) {
        // System.out.println(new Solution().longestPalindrome("aa"));
        // System.out.println(new Solution().longestPalindrome("aaaa"));
        // System.out.println(new Solution().longestPalindrome("aaa"));
        System.out.println(new Solution().longestPalindrome("babad"));
        System.out.println(new Solution().longestPalindrome("saaxaabaaxaamzs"));
    }

    private static class Solution {
        public boolean longestPalindrome(String s) {
            if (s == null || s.length() <= 1) return true;
            String result1 = longestPalindromeV3(s);
            String result2 = longestPalindromeV4(s);
            System.out.println("result1 = "+result1+", result2 = "+result2);
            return result1.equalsIgnoreCase(result2);
        }

        private String longestPalindromeV4(String s) {
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundCenter(s, i, i);
                int len2 = expandAroundCenter(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > end - start) {
                    start = i - (len - 1) / 2;
                    end = i + len / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            int L = left, R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;
                R++;
            }
            return R - L - 1;
        }

        /**
         * 以一点为中心, 判断从这点向两侧可以得到的回文子串
         * 长度为n的字符串, 存在2*n-1个中心, 中心可能存在于两个字符之间
         */
        private String longestPalindromeV3(String str) {
            int position = 0;
            int maxLength = 0;
            int length = str.length();

            for (int i = 0; i < 2 * length - 1; i++) {
                int l = maxLengthAroundPosition(i, str, length);
                if (l > maxLength) {
                    position = i;
                    maxLength = l;
                }
            }

            return substringAround(position, maxLength, str);
        }

        /**
         * 以位置position为中心可以得到的最长回文子串的长度
         *
         * @param position 回文子串的中心, 可能在两个字符之间
         * @param str 原字符串
         * @param length 原字符串长度
         * @return 子回文串长度
         */
        private int maxLengthAroundPosition(int position, String str, int length) {
            int start, end;
            if ((position & 1) == 0) start = end = position / 2;
            else {
                start = (position - 1) / 2;
                end = (position + 1) / 2;
            }
            int range = 0;

            while (start >=0 && end < length && str.charAt(start) == str.charAt(end)) {
                start--;
                end++;
                range++;
            }

            return (position & 1) == 0 ? 2 * range - 1 : 2 * range;
        }

        /**
         * 从原字符串str中获取以position为中心长度为length的回文串内容
         *
         * @param position 中心位置, 可能在两个字符之间
         * @param length 子回文串长度
         * @param str 原字符串
         * @return 子回文串内容
         */
        private String substringAround(int position, int length, String str) {
            int start, end;
            if ((position & 1) == 0) {
                int range = (length - 1) / 2;
                int i = position / 2;
                start = i - range;
                end = i + range;
            } else {
                int range = length / 2;
                int i = (position - 1) / 2;
                start = i - range + 1;
                end = i + range;
            }

            return str.substring(start, end+1);
        }

        /**
         * 动态规划, 设计有问题,"aaaa"情况会出错
         * */
        private String longestPalindromeV2(String str) {
            int maxLength = 0;
            int index = 0;
            int length = str.length();
            int[] lengthMap = new int[length];

            for (int i = 0; i<length; i++) {
                int l = maxLengthAroundChar(str, length, i);
                if (l > maxLength) {
                    maxLength = l;
                    index = i;
                }

                lengthMap[i] = l;
            }

            for (int i = 0; i<length; i++) {
                if ((lengthMap[i] & 1) == 1) continue;
                int l = maxLengthAroundEvenSubstring(str, length, i);
                if (l > maxLength) {
                    maxLength = l;
                    index = i;
                }
            }

            if ((maxLength & 1) == 0)
                return str.substring(index-(maxLength-2)/2, index+2+(maxLength-2)/2);

            return str.substring(index-(maxLength-1)/2, index+(maxLength-1)/2+1);
        }

        /**
         * 从一个位置的字符开始向两边可以得到的最长回文串长度
         *
         * @param str 原字符串
         * @param length 原字符串长度
         * @param position 字符位置
         * @return 以该字符为中心的最长回文子串的长度
         */
        private int maxLengthAroundChar(String str, int length, int position) {
            if (position == length - 1) return 1;
            if (position == 0) return str.charAt(position) == str.charAt(position+1) ? 2 : 1;

            int range = 1;
            while (position - range >=0 && position + range < length && str.charAt(position - range) == str.charAt(position + range)) range++;

            if (range == 1 && position+1 < length && str.charAt(position)==str.charAt(position+1)) return 2;
            return (range-1)*2+1;
        }

        /**
         * 长度为2的子回文串向原字符串两边扩展, 可能得到的最长回文子串长度
         *
         * @param str 原字符串
         * @param length 原字符串长度
         * @param position 长度为2的回文串的首个字符在原字符串中的位置
         * @return 基于长度为2的子回文串的最长子回文串长度
         */
        private int maxLengthAroundEvenSubstring(String str, int length, int position) {
            if (position == 0) return 2;

            int range = 1;
            while(position-range>=0 && position+1+range<length && str.charAt(position-range)==str.charAt(position+1+range)) range++;
            return range*2;
        }


        /**
         * 穷举法判断最长子回文串
         * */
        private String longestPalindromeV1(String str) {
            int maxLength = 0;
            int i = 0, j = 0;
            int l = str.length();

            for (int s = 0; s < l - 1; s++) {
                for (int e = s+1; e < l; e++) {
                    if (isPalindrome(str, s, e)) {
                        int length = e - s + 1;
                        if (maxLength < length) {
                            maxLength = length;
                            i = s;
                            j = e;
                        }
                    }
                }
            }

            return str.substring(i, j+1);
        }

        /**
         * 从s到e位置的子字符串是否是回文
         *
         * @param str 原字符串
         * @param s 起始位置
         * @param e 结束位置
         * @return true表示是回文
         */
        private boolean isPalindrome(String str, int s, int e) {
            if (s >= e) return false;

            while(s <= e) {
                if (str.charAt(s++) != str.charAt(e--))
                    return false;
            }

            return true;
        }
    }
}