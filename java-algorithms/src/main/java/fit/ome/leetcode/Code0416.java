package fit.ome.leetcode;

import java.util.Arrays;

/**
 * 分割等和子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * <p>
 * 升序排列
 * 构建前缀和数组，查找另一半
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/23
 **/
public class Code0416 {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }

        int sumVal = 0, maxNum = 0;
        for (int i = 0; i < len; i++) {
            sumVal += nums[i];
            maxNum = Math.max(maxNum, nums[i]);
        }
        if ((sumVal & 1) == 1) {
            return false;
        }
        int target = sumVal >> 1;
        if (maxNum > target) {
            return false;
        }

        boolean[][] dp = new boolean[len][target + 1];
        dp[0][nums[0]] = true;
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] | dp[i-1][j - nums[i]];
                } else if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }


        return dp[len - 1][target];
    }
}
