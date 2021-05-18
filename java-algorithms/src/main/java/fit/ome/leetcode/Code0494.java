package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 目标和
 * <p>
 * https://leetcode-cn.com/problems/target-sum/
 */
public class Code0494 {
    public int findTargetSumWays(int[] nums, int target) {


        return process(nums, 0, target);
    }

    public int process(int[] nums, int i, int target) {
        if (i == nums.length) {
            return target == 0 ? 1 : 0;
        }
        int ans = 0;
        ans += process(nums, i + 1, target - nums[i]);
        ans += process(nums, i + 1, target + nums[i]);
        return ans;
    }

    // =========== 改动态规划
    // j 可能为负数，调大起始坐标即可
    public int findTargetSumWays1(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

        }

        return process(nums, 0, target);
    }
    // 可以进行空间优化，使用两个一维数组

}
