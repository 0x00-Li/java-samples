package fit.ome.leetcode;

/**
 * 移动零
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/4
 **/
public class Code0283 {
    public void moveZeroes(int[] nums) {
        int zeroStart = -1;
        int zeroEnd = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroStart = zeroStart == -1 ? i : zeroStart;
                zeroEnd = i;
            } else if (zeroStart >= 0) {
                swap(nums, zeroStart, i);
                zeroStart++;
                zeroEnd = i;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
