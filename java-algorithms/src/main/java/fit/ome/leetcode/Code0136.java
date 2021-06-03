package fit.ome.leetcode;

/**
 * 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 */
public class Code0136 {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
