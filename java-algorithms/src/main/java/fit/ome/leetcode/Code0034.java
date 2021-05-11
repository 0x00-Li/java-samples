package fit.ome.leetcode;

/**
 * 超找数组 中的目标值范围
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/11
 **/
public class Code0034 {
    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return arr;
        }

        int l = 0;
        int r = nums.length - 1;


        while (l <= r) {

            if (nums[(l + r) >> 1] == target) {
                break;
            } else if (nums[(l + r) >> 1] < target) {
                l = ((l + r) >> 1) + 1;
            } else if (nums[(l + r) >> 1] > target) {
                r = ((l + r) >> 1) - 1;
            }
        }

        if (l>=0&&r>=0&&nums[(l + r) >> 1] == target) {
            while (l < r) {
                if (nums[l] == target) {
                    break;
                }
                l++;
            }
            while (l < r) {
                if (nums[r] == target) {
                    break;
                }
                r--;
            }
            arr[0] = l;
            arr[1] = r;
            return arr;
        } else {
            return arr;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Code0034().searchRange(new int[]{1}, 1));
//        System.out.println(new Code0034().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
    }
}
