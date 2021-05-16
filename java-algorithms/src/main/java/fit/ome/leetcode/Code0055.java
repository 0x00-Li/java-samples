package fit.ome.leetcode;

/**
 * 跳跃游戏
 * <p>
 * https://leetcode-cn.com/problems/jump-game/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/15
 **/
public class Code0055 {
    // 暴力递归解
    public boolean canJump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return true;
        }
        return process(nums, nums.length - 1);
    }

    private boolean process(int[] nums, int i) {
        if (i == 0) {
            return true;
        }
        if (i < 0) {
            return true;
        }
        boolean res = false;
        for (int j = i - 1; j >= 0; j--) {
            if (nums[j] >= i - j) {
                res = process(nums, j);
            } else {
                res = false;
                break;
            }

        }
        return res;
    }

    public boolean canJump2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return true;
        }
        boolean[][] dp = new boolean[nums.length - 1][nums.length - 1];

        return process2(nums, nums.length - 2, nums.length - 1);
    }

    // 从 i 位置是否可以到到t位置
    private boolean process2(int[] nums, int i, int t) {
        if (i < 0) {
            return true;
        }
        boolean ans = false;
        int j = i;
        while (j >= 0) {
            if (nums[j] >= t - j) {
                ans = process2(nums, j - 1, j);
                break;
            }
            j--;
        }
        if (j == 0 && ans) {
            return ans;
        }
        return ans;
    }

    public boolean canJump3(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return true;
        }

        int cur = 0;
        int maxR = -1;
        int curR = 0;
        while (cur < nums.length) {
            curR = nums[cur] + cur;

            maxR = Math.max(maxR, curR);
            if (maxR >= nums.length - 1) {
                return true;
            }
            if (curR == cur && maxR <= curR) {
                return false;
            }
            cur++;
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(new Code0055().canJump3(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new Code0055().canJump3(new int[]{3, 2, 1, 0, 4}));
        System.out.println(new Code0055().canJump3(new int[]{0, 1}));
        System.out.println(new Code0055().canJump3(new int[]{1, 2, 0, 1}));
        System.out.println(new Code0055().canJump3(new int[]{0, 2, 3}));
        System.out.println(new Code0055().canJump3(new int[]{3,0,8,2,0,0,1}));
    }

    // 改动态规划
    public boolean canJump1(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return true;
        }
        // dp[i] 表示从i位置开始，可以跳到最后位置
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        process1(nums, 0, dp);
        return dp[0];
    }

    private void process1(int[] nums, int i, boolean[] dp) {

        for (int j = nums.length - 2; j >= 0; j--) {
            if (nums[j] >= nums.length - j) {
                dp[j] = true;
            }
        }

    }
}
