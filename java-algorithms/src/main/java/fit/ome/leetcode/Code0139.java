package fit.ome.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 * <p>
 * 回溯算法
 * <p>
 * 动态规划
 */
public class Code0139 {
    public boolean wordBreak1(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];// 对应s的每个位置是否可以拼出,求最后一位是否可以拼出
        int nextIndex = 0;
        for (String word : wordDict) {
            if (word.length() - 1<s.length()&&!dp[word.length() - 1]) {
                dp[word.length() - 1] = s.startsWith(word);
            }
            nextIndex = Math.min(nextIndex, word.length());
        }
        while (nextIndex < s.length()) {
            for (String word : wordDict) {
                int start = nextIndex - word.length()+1;
                if (start - 1 >= 0) {
                    if(!dp[nextIndex]){
                        dp[nextIndex] = dp[start - 1] && word.equals(s.substring(start, nextIndex + 1));
                    }
                }
            }
            nextIndex++;
        }
        return dp[s.length() - 1];
    }

    public boolean process(String s, List<String> wordDict, int[] dp, int index) {
        if (index == s.length()) {
            return true;
        }

        int nextIndex = index + 1;
        for (String word : wordDict) {
            if (index + word.length() - 1 < s.length() && dp[index + word.length() - 1] == 0) {
                dp[index + word.length() - 1] = word.equals(s.substring(index, index + word.length())) ? 1 : -1;
                nextIndex = Math.min(nextIndex, index + word.length());
            }
        }
        process(s, wordDict, dp, nextIndex);
        return dp[s.length() - 1] == 1;
    }

    // 超时
    public boolean wordBreak(String s, List<String> wordDict) {
        return backtrack(s, new HashSet<>(wordDict), 0);
    }

    public boolean backtrack(String s, Set<String> wordDict, int index) {
        if (index == s.length()) {
            return true;
        }
        boolean ans = false;
        for (String d : wordDict) {
            if (index + d.length() <= s.length() && d.equals(s.substring(index, index + d.length()))) {
                ans = backtrack(s, wordDict, index + d.length());
                if (ans) {
                    break;
                }
            }
        }
        return ans;
    }


}
