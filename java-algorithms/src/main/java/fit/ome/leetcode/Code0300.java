package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/6
 **/
public class Code0300 {
    //O(n2)
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        if (nums.length == 0) {
            return ans;
        }


        int n = nums.length;
        int[] dp = new int[n];
        dp[0]=1;
        ans=1;
        for (int i = 1; i < n; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans=Math.max(ans,dp[i]);
        }

        return ans;
    }
}
