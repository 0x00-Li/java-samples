package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 * https://leetcode-cn.com/problems/subsets/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/21
 **/
public class Code0078 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(nums, 0, path, res);

        return res;
    }

    public void backtrack(int[] nums, int i, List<Integer> path, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 要当前字符
        path.add(nums[i]);
        backtrack(nums, i + 1, path, res);
        // 不要当前字符
        path.remove(path.size() - 1);
        backtrack(nums, i + 1, path, res);
    }
}
