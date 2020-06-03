package com.gemini.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * com.gemini.leetcode.LeetCode290_WordPattern
 * <p>
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。   
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode290_WordPattern {

    public static boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        String[] array = str.split(" ");
        if (pattern.length() != array.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char key = pattern.charAt(i);
            //当前 key 是否存在
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array[i])) {
                    return false;
                }
            } else {
                //判断 value 中是否存在
                if (map.containsValue(array[i])) {
                    return false;
                } else {
                    map.put(key, array[i]);
                }

            }
        }
        return true;

    }

    // 这种解法是真滴秒
    //他利用了 put 的返回值，如果 put 的 key 不存在，那么就插入成功，返回 null。
    //
    //如果 put 的 key 已经存在了，返回 key 是之前对应的 value。
    //
    //所以第一次遇到的单词（字母）两者都会返回 null，不会进入 return false。
    //
    //第二次遇到的单词（字母）就判断之前插入的 value 是否相等。也有可能其中一个之前还没插入值，那就是 null ，另一个之前已经插入了，会得到一个 value，两者一定不相等，就会返回 false。
    //
    //还有一点需要注意，for 循环中我们使用的是 Integer i，而不是 int i。是因为 map 中的 value 只能是 Integer 。
    //
    //如果我们用 int i 的话，java 会自动装箱，转成 Integer。这样就会带来一个问题，put 返回的是一个 Integer ，判断对象相等的话，相当于判断的是引用的地址，那么即使 Integer 包含的值相等，那么它俩也不会相等。我们可以改成 int i 后试一试。
    //改成 int i 以后，就不能 AC 了。但你会发现当 pattern 的长度比较小时，代码是没有问题的，这又是为什么呢？
    //
    //是因为当数字在 [-128,127] 的范围内时，对于同一个值，Integer 对象是共享的，举个例子。
    //
    //
    //作者：windliang
    //链接：https://leetcode-cn.com/problems/word-pattern/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--53/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    public boolean wordPattern3(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (int i=0; i<words.length; ++i)
            if (!Objects.equals(index.put(pattern.charAt(i), i),
                    index.put(words[i], i)))
                return false;
        return true;
    }




    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog dog dog dog"));
    }
}
