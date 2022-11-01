package com.gemini.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。
 *  对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/word-ladder">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode127_LadderLength {

    // BFS
    // 1. beginWord入队列
    // 2. 从队列头部取出word，计算该单词可能的变种word（变种只可以从wordList中取）
    // 3. 如果该变种word中，等于endWord,直接返回结果,如果不等于endWord，则加入队列尾部（为避免重复记录，需要一个哈希表记录已经处理过的变种word，处理过的直接跳过）
    // 4. 遍历完该word的所有变种word后继续开始下一轮循环，从队列头取出下一个word
    // 5. 如果队列为空，则说明算法结束，未找到结果，结束
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(beginWord);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> nextWords = get(cur, wordList);
                if (nextWords != null && nextWords.size() > 0) {
                    for (String nextWord : nextWords) {
                        if (!visited.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            queue.offer(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
            }
        }
        return 0;
    }

    // 双向BFS
    //
    // d1、d2 为两个方向的队列
    // m1、m2 为两个方向的哈希表，记录每个节点距离起点距离的
    // 只有两个队列都不空，才有必要继续往下搜索
    // 如果其中一个队列空了，说明从某个方向搜到底都搜索不到该方向的目标节点
    // while (!d1.isEmpty() && !d2.isEmpty()) {
    //      if (d1.size() < d2.size()) {
    //          update(d1, m1, m2);
    //      } else {
    //          update(d2, m2, m1);
    //      }
    // }
    //
    // void update(Queue d, Map cur, Map other) {}
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.offer(beginWord);
        q2.offer(endWord);

        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();

        m1.put(beginWord, 0);
        m2.put(endWord, 0);
        int result = -1;
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.size() <= q2.size()) {
                result = update(q1, m1, m2, wordList);
            } else {
                result = update(q2, m2, m1, wordList);
            }
            if (result != -1) {
                return result + 1;
            }
        }
        return 0;
    }

    // 双向BFS其中处理的内部逻辑是类似的
    // 1. 从当前处理的队列头中取出word
    // 2. 获取该word的可能变种List<String> nextWords
    // 3. 遍历nextWords，已经处理过的直接跳过，否则判断另外一个方向的处理过程中是否处理过该变种word
    // 4. 如果是的话，直接返回结果， 不是的话，记录一下该变种word已经处理过，并且将该word加入队列
    //
    public int update(Queue<String> q, Map<String, Integer> cur, Map<String, Integer> other, List<String> wordList) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            String word = q.poll();
            int step = cur.get(word);
            List<String> nextWords = get(word, wordList);
            if (nextWords != null && !nextWords.isEmpty()) {
                for (String nextWord : nextWords) {
                    if (cur.containsKey(nextWord)) {
                        continue;
                    }

                    if (other.containsKey(nextWord)) {
                        return step + 1 + other.get(nextWord);
                    } else {
                        cur.put(nextWord, step + 1);
                        q.offer(nextWord);
                    }
                }

            }
        }
        return -1;
    }

    public List<String> get(String word, List<String> wordList) {
        return wordList.stream().filter(s -> check(word, s)).collect(Collectors.toList());
    }

    public boolean check(String word, String other) {
        if (word.length() != other.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != other.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }

}
