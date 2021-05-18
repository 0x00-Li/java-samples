package fit.ome.leetcode;

/**
 * 爬楼梯
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/18
 **/
public class Code0070 {
    // 超时
    public int climbStairs(int n) {
        return process(n, 0);
    }

    // i 到了第几阶
    public int process(int n, int i) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        int ans = 0;
        ans += process(n, i + 1);
        ans += process(n, i + 2);
        return ans;
    }

    public int climbStairs1(int n) {
        return process(n, n);
    }

    // i 到了第几阶
    public int process1(int n, int i) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        int ans = 0;
        ans += process1(n, i - 1);
        ans += process1(n, i - 2);
        return ans;
    }

    // ===================
    // 动态规划，优化
    public int climbStairs2(int n) {
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        // 到达第i阶台阶，有多少中方法
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1]=2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
}
