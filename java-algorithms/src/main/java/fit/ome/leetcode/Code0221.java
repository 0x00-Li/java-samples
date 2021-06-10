package fit.ome.leetcode;

/**
 * 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class Code0221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 以mn 为右下定点的时候的可用正方形的边长
        int[][] dp = new int[m][n];//
        int ans = 0;
        // 第一列都以自己的实际面积为准
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0]=='1'?1:0;
            ans = Math.max(dp[i][0], ans);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i]=='1'?1:0;
            ans = Math.max(dp[0][i], ans);
        }
        // 根据依赖计算自己的面积
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
                ans = Math.max(ans, dp[i][j] * dp[i][j]);
            }
        }
        return ans;
    }
}
