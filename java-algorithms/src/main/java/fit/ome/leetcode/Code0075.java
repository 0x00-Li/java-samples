package fit.ome.leetcode;

/**
 * 颜色分类问题
 * <p>
 * https://leetcode-cn.com/problems/sort-colors/
 * 类似荷兰国旗问题
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/19
 **/
public class Code0075 {
    public void sortColors(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int less = -1;
        int more = nums.length;
        int i = 0;
        while (less < more && i < more) {
            if (nums[i] < 1) {
                swap(nums, i++, ++less);
            } else if (nums[i] > 1) {
                swap(nums, i, --more);
            } else {
                i++;
            }
        }

    }

    public void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        new Code0075().sortColors(new int[]{});
    }
}
