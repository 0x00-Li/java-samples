package fit.ome.leetcode;

/**
 * 最大乘积子数组
 */
public class Code0152 {
    // 压缩空间
    public int maxProduct1(int[] nums) {
        int len = nums.length;
        //f(i,j) i~j 最的乘积
        //f(i,j) =f(i,j-1)*nums[j]
        int[] dp0 = new int[len];

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dp0[i] = nums[i];
            ans = Math.max(dp0[i], ans);
        }
        for (int i = 0; i < len; i++) {
            int[] dp1 = new int[len];
            for (int j = i + 1; j < len; j++) {
                dp1[j] = dp0[j - 1] * nums[j];
                ans = Math.max(dp1[j], ans);
                dp0[j-1] = dp1[j-1];
            }
            dp0[len-1]=dp1[len-1];
        }
        return ans;
    }

    // 超出内存
    public int maxProduct(int[] nums) {
        int len = nums.length;
        //f(i,j) i~j 最的乘积
        //f(i,j) =f(i,j-1)*nums[j]
        int[][] dp = new int[len][len];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
            ans = Math.max(dp[i][i], ans);
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j - 1] * nums[j];
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }
}