package fit.ome.leetcode;

import java.util.Arrays;

/**
 * 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 * <p>
 * 回溯算法
 * 剪枝
 * <p>
 * ---
 * 动态规划
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/2
 **/
public class Code0322 {
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);// 排序

        return process2(coins, amount, new int[amount]);
    }

    public int process2(int[] coins, int rest, int[] dp) {
        if (rest < 0) {
            return -1;
        }
        if (rest == 0) {
            return 0;
        }
        if (dp[rest - 1] != 0) {
            return dp[rest - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = process2(coins, rest - coin, dp);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        dp[rest - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rest - 1];

    }

    // ----
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);// 排序

        for (int i = coins.length - 1; i >= 0; i--) {
            int process = process1(coins, i, amount, 0);
            if (process > 0) {
                return process;
            }

        }
        return -1;
    }

    public int process1(int[] coins, int index, int rest, int count) {
        if (rest == 0) {
            return count;
        }
        if (index == -1) {
            return -1;
        }

        int c1 = -1;
        if (rest - coins[index] >= 0) {
            c1 = process1(coins, index, rest - coins[index], count + 1);// 要当前的硬币
        }
        int c2 = process1(coins, index - 1, rest, count);// 不要当前的硬币
        if (c1 * c2 > 0 && c1 != -1) {
            return Math.min(c1, c2);
        } else {
            return c1 < 0 ? c2 : c1;
        }


    }

    // 暴力解
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);// 排序
        int c = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int process = process(coins, i, amount, 0);
            if (process > 0) {
                c = Math.min(c, process);
            }

        }
        return c == Integer.MAX_VALUE ? -1 : c;
    }

    public int process(int[] coins, int index, int rest, int count) {
        if (rest == 0) {
            return count;
        }
        if (index == coins.length) {
            return -1;
        }


        if (rest < coins[index]) {
            return -1;
        }

        int c1 = process(coins, index, rest - coins[index], count + 1);// 要当前的硬币
        int c2 = process(coins, index + 1, rest, count);// 不要当前的硬币
        if (c1 * c2 > 0) {
            return Math.min(c1, c2);
        } else {
            return c1 < 0 ? c2 : c1;
        }


    }
}
