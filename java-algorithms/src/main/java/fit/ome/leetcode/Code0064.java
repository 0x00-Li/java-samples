package fit.ome.leetcode;

/**
 * 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class Code0064 {
    // 动态规划，求出最小值
    public int minPathSum(int[][] grid) {
        // dp[i][j] 从（0，0） -> (i,j) 的最小路径
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j]=Math.min(dp[i][j-1],dp[i-1][j])+grid[i][j];
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
