package fit.ome.leetcode;

import java.util.Arrays;

/**
 * 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * 两个数XOR等于0
 */
public class Code0287 {
    public int findDuplicate1(int[] nums) {
        // 快慢指针
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[slow]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate(int[] nums) {

        Arrays.sort(nums);


        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }
}
