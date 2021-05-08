package fit.ome.leetcode;

import java.util.*;

/**
 * 三数和问题
 * <p>
 * 思路
 * <p>
 * 三数和为0
 * <p>
 * 说明其中两数和等于第三数的相反数，先明确第三数，寻找其中前两数
 * 先对数组进行排序，明确数字数量3000
 */
public class Code0015 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        // 排序，保证数组内的单调性
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        // 三元组中有序的前两个值
        Set<String> exists = new HashSet<>();

        process(nums, 0, 1, res, exists);
        return res;
    }

    /**
     * 全部向后判断，不向前判断
     *
     * @param nums
     * @param first
     * @param second
     * @param res
     * @param exists
     */
    private void process(int[] nums, int first, int second, List<List<Integer>> res, Set<String> exists) {
        if (first == nums.length - 1) {
            return;
        }
        if (second == nums.length - 1) {
            process(nums, first + 1, first + 2, res, exists);
            return;
        }
        if (exists.contains(nums[first] + "#" + nums[second])) {
            // 已经产生过
            process(nums, first, second + 1, res, exists);
            return;
        }
        if (nums[first] + nums[second] + nums[nums.length - 1] < 0) {
// 不可用，但是已经判断
            exists.add(nums[first] + "#" + nums[second]);
            process(nums, first, second + 1, res, exists);
            return;
        }
        int third = -1;
        if ((third = existsNum(nums, second + 1, nums.length - 1, -(nums[first] + nums[second]))) > -1) {

            List<Integer> item = new ArrayList<>();
            item.add(nums[first]);
            item.add(nums[second]);
            item.add(nums[third]);
            res.add(item);
        }
        exists.add(nums[first] + "#" + nums[second]);
        process(nums, first, second + 1, res, exists);


    }

    /**
     * 在指定区间是否存在n
     * 闭区间 [i,j]
     *
     * @param nums
     * @param i
     * @param j
     * @param n
     * @return
     */
    private int existsNum(int[] nums, int i, int j, int n) {

        while (i <= j) {
            int mid = (j + i + 1) >> 1;
            if (nums[mid] == n) {
                return mid;
            } else if (nums[mid] < n) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }

    // 暴力方法超时，需要优化
    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        // 排序，保证数组内的单调性
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();


        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            process1(nums, i, res);
            while ((i + 1 < nums.length - 1) && nums[i + 1] == nums[i]) {
                i++;
            }
        }


        return res;
    }

    /**
     * 全部向后判断，不向前判断
     *
     * @param nums
     * @param i
     * @param res
     */
    private void process1(int[] nums, int i, List<List<Integer>> res) {
        if (i > nums.length - 3) {
            return;
        }
        int l = i + 1;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[i] + nums[l] + nums[r] == 0) {
                List<Integer> item = new ArrayList<>();
                item.add(nums[i]);
                item.add(nums[l]);
                item.add(nums[r]);
                res.add(item);
                while ((l + 1 < nums.length - 1) && nums[l] == nums[++l]) {

                }
                while ((r - 1 > 0) && nums[r] == nums[--r]) {

                }

            } else if (nums[i] + nums[l] + nums[r] > 0) {
                r--;
            } else if (nums[i] + nums[l] + nums[r] < 0) {
                l++;
            }
        }

    }


    public static void main(String[] args) {
//        System.out.println(new Code0015().threeSum1(new int[]{0,0,0}));
        System.out.println(new Code0015().threeSum1(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
        System.out.println(new Code0015().threeSum1(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
