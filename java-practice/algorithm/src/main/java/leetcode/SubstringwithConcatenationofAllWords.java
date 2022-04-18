package leetcode;

import java.util.*;

/**
 * Description: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * @author youyou
 * @date 10/25/21 3:19 PM
 */
public class SubstringwithConcatenationofAllWords {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}));
    }

    private static class Solution {
        /**
         * Description: 获取字符串`s`中由`words`中所有元素组成的子字符串的起始位置.
         *
         * @param s 原始字符串
         * @param words words数组
         * @return 子字符串的起始索引位置
         * @author youyou
         * @date 10/25/21 6:08 PM
         */
        public List<Integer> findSubstring(String s, String[] words) {
            int length = words[0].length();
            // index遍历的截止位置
            int endPoint = s.length() - length * words.length;
            // 为了方便判断子串是否在`words`中, 将`words`格式化为hashmap.
            // 其中key: words中的每一个元素. value: 这个元素在`words`中的个数.
            HashMap<String, Integer> wordsMap = new HashMap<>(words.length << 2);
            for (String word : words) {
                wordsMap.put(word, wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
            }
            List<Integer> startingIndex = new ArrayList<>();

            for (int i = 0; i <= endPoint; i++) {
                String subString = s.substring(i, i + length);
                if (!wordsMap.containsKey(subString))
                    continue;

                // removing substring from subMap
                HashMap<String, Integer> subMap = new HashMap<>(wordsMap);
                removeWord(subString, subMap);
                if (isSubstring(s.substring(i + length), subMap, length))
                    startingIndex.add(i);
            }

            return startingIndex;
        }

        /**
         * Description: 第`5`步的实现 - 判断`s`中接下来长度为`l*(words.length()-1)`的子串(`[index, index+l*(words.length()-1))`)
         * 是否是`words`的剩余字符串的连接的结果.
         *
         * @param s `s`的子字符串
         * @param wordsMap `words`组成的map
         * @param wordLength `words`中每个元素的长度
         * @return 如果字符串`s`是`words`中所有元素的组合, 返回`true`. 否则返回`false`
         * @author youyou
         * @date 10/25/21 6:05 PM
         */
        private boolean isSubstring(String s, HashMap<String, Integer> wordsMap, int wordLength) {
            // 当且仅当`wordsMap`和`s`都为空时, 才停止循环
            while (!wordsMap.isEmpty() && !s.isEmpty()) {
                String subString = s.substring(0, wordLength);
                if (!wordsMap.containsKey(subString)) break;

                removeWord(subString, wordsMap);
                s = s.substring(wordLength);
            }

            // 通过判断`wordsMap`中是否还有元素去判断`s`与`words`是否完全匹配.
            return wordsMap.isEmpty();
        }

        /**
         * Description: 从`words`中移除匹配过的字符串
         *
         * @param word 匹配过的字符串
         * @param wordsMap `words`所在的map
         * @author youyou
         * @date 10/25/21 6:04 PM
         */
        private void removeWord(String word, HashMap<String, Integer> wordsMap) {
            if (wordsMap.get(word) == 1)
                wordsMap.remove(word);
            else
                // 如果word在`wordsMap`中出现多次, 则次数-1
                wordsMap.put(word, wordsMap.get(word) - 1);
        }

        // public List<Integer> findSubstring(String s, String[] words) {
        //     int length = words[0].length();
        //     int endPoint = s.length() - length * words.length;
        //     // set cannot store repeat words.
        //     HashSet<String> wordsSet = new HashSet<>(Arrays.asList(words));
        //     List<Integer> startingIndex = new ArrayList<>();
        //
        //     for (int i = 0; i <= endPoint; i++) {
        //         String subString = s.substring(i, i + length);
        //         if (!wordsSet.contains(subString))
        //             continue;
        //
        //         // wordsSet remove substring
        //         // 使用map可以不需要创建这么多对象
        //         HashSet<String> subSet = new HashSet<>(wordsSet);
        //         subSet.remove(subString);
        //         if (isSubstring(s.substring(i + length), subSet, length))
        //             startingIndex.add(i);
        //     }
        //
        //     return startingIndex;
        // }
        //
        // private boolean isSubstring(String s, HashSet<String> wordsSet, int wordLength) {
        //     while (!wordsSet.isEmpty() && !s.isEmpty()) {
        //         String subString = s.substring(0, wordLength);
        //         if (!wordsSet.contains(subString)) return false;
        //
        //         wordsSet.remove(subString);
        //         s = s.substring(wordLength);
        //     }
        //
        //     return wordsSet.isEmpty();
        // }
    }
}
