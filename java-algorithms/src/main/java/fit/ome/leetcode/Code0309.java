package fit.ome.leetcode;

import java.util.Arrays;

/**
 * 最佳买卖股票时机含冷冻期
 * <p>
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * 回溯
 * <p>
 * 动态规划
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/5
 **/
public class Code0309 {
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;

        for (int i = 1; i < n; i++) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }
        return Math.max(f1, f2);
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        // [i][0] - 手上持有股票的最大收益
        // [i][1] - 手上未持有股票，处于冻结期的最大收益
        // [i][2] - 手上未持有股票，且非冻结期的最大收益
        int[][] dp = new int[n][3];// 每个位置卖，和买的最大收益
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public int maxProfit(int[] prices) {


        return backtrack(prices, 0, -1, 0, false);
    }

    // opIndex 最后一次操作的位置
    // buyState 当前状态
    public int backtrack(int[] prices, int index, int opIndex, int total, boolean buyState) {
        if (index == prices.length) {
            return total;
        }
        int ans = Integer.MIN_VALUE;
        if (buyState) {
            // 当前处于买进状态
            if (prices[index] >= prices[opIndex]) {
                // 不亏损才可能卖出
                // 卖出
                ans = Math.max(ans, backtrack(prices, index + 1, index, total + (prices[index] - prices[opIndex]), false));
                // 不卖出
                ans = Math.max(ans, backtrack(prices, index + 1, opIndex, total, true));
            } else {
                // 不卖出
                ans = Math.max(backtrack(prices, index + 1, opIndex, total, true), ans);
            }
        } else {
            // 当前处于卖出状态

            if (index == 0 || index - 1 > opIndex) {
                // 可买进状态
                //买进
                ans = Math.max(ans, backtrack(prices, index + 1, index, total, true));
// 不买进
                ans = Math.max(ans, backtrack(prices, index + 1, opIndex, total, false));
            } else {
                //                不可买进状态
                ans = Math.max(ans, backtrack(prices, index + 1, opIndex, total, false));
            }
        }
        return ans;
    }

}
