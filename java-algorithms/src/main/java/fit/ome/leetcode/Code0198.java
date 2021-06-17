package fit.ome.leetcode;

/**
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 */
public class Code0198 {
    // 压缩空间
    public int rob2(int[] nums) {

        // f(i) 当前位置的可获得的最大值
        // f(i)=
        // i-1 要了
        // f(i-1)=f(i-2)+nums[i-1]
        // i-1 没要
        // f(i-1)=f(i-2)

        // 0 当前位置不要的最大值
        // 1 当前位置要了的最大值
        int len = nums.length;
        int p0 = 0;
        int p1 = nums[0];
        for (int i = 1; i < len; i++) {

            int ans0 = Math.max(p0, p1);
            int ans1 = Math.max(p0 + nums[i], p1);
            p0=ans0;
            p1=ans1;
        }
        return Math.max(p0,p1);
    }

    // 动态规划
    public int rob1(int[] nums) {

        // f(i) 当前位置的可获得的最大值
        // f(i)=
        // i-1 要了
        // f(i-1)=f(i-2)+nums[i-1]
        // i-1 没要
        // f(i-1)=f(i-2)

        // 0 当前位置不要的最大值
        // 1 当前位置要了的最大值
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        new Code0198().rob1(new int[]{1, 2, 3, 1});
    }

    // 超时
    public int rob(int[] nums) {
        return get(nums, 0, false);
    }

    public int get(int[] nums, int index, boolean preGet) {
        if (index == nums.length) {
            return 0;
        }
        int p1 = 0;
        int p2 = get(nums, index + 1, false);
        if (!preGet) {
            // 要当前
            p1 += nums[index] + get(nums, index + 1, true);
        }
        return Math.max(p1, p2);
    }
}
