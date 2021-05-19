package fit.ome.leetcode;

/**
 * 编辑距离
 * <p>
 * https://leetcode-cn.com/problems/edit-distance/
 * <p>
 * dfs
 * <p>
 * 和动态规划
 */
public class Code0072 {
    // 动态规划
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 * len2 == 0) {
            // 有一个空字符串
            return Math.max(len1, len2);
        }

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int left=dp[i][j-1]+1;
                int down=dp[i-1][j]+1;
                int left_down=dp[i-1][j-1];
                if (word2.charAt(j-1) != word1.charAt(i-1)) {
                  left_down++;
                }
                dp[i][j] = Math.min(left,Math.min(down,left_down));
            }
        }
        return dp[len1][len2];
    }
}
