---
layout: post
title:  "LeetCode - Substring with Concatenation of All Words"
date:   2021-10-25 16:40:41 +0800
categories: leetcode
---
这篇文章是用来记录`LeetCode`30题: [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)的解题过程.

## 问题
You are given a string `s` and an array of strings `words` of **the same length**. Return all starting indices of substring(s) in `s` that is a concatenation of each word in `words` **exactly once**, **in any order**, and **without any intervening characters**.

You can return the answer in **any order.**
   
---

## 测试用例
### 用例1
> Input: s = "barfoothefoobarman", words = ["foo","bar"]   
Output: [0,9]   
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.   
The output order does not matter, returning [9,0] is fine too.

### 用例2
> Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]    
Output: []

### 用例3
> Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]    
Output: [6,9,12]

---

## 思路
1. 获取`words`中任一元素的长度`l`.
2. 设置字符串`s`的索引`index=0`.
3. 判断长度为`l`的子串(`[index, index+l)`)是否在`words`中.
4. 如果不在, `index`向后移动一位, 重复执行第3步.
5. 如果在, 判断`s`中接下来长度为`l*(words.length()-1)`的子串(`[index, index+l*(words.length()-1))`)是否是`words`的剩余字符串的连接的结果.
6. 如果不是, `index`向后移动一位, 重复执行第3步.
7. 如果是, 那么`index`就是其中的一个答案.


补充:
- `index`的截止位置:
  - 要确保`[index, s.length()) >= l*words.length()`才有可能在字符串`s`以`index`为开头的子串中找到答案.
  - 所以截止位置为`s.length() - l*words.length()`.
- 确保`words`中的每一个元素只在子串中匹配道一次:
  - 每次判断到`s`的子串在`words`中后, 总`words`中移除这个元素.
  - 因为`index`每后移一位, 就需要重新判断. **所以每个`index`必须有自己对应的`words`数组.**
- 第`5`步的实现方式:
  - 将`s`的子串按照长度`l`切割, 判断每一个切割后的子串是否在`words`中.
  - 如果有不存在的, 则不符合要求.
  - 如果全部存在, 则符合要求.
  - 注意: **每匹配都一个`words`中的元素, 都要从`words`中将该元素移除**.

---

## 实现
```java
class Solution {
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
}
```
