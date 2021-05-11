package fit.ome.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 * <p>
 * 回溯
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/11
 **/
public class Code0039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, 0, path, res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (index == candidates.length) {
            return;
        }
        if (candidates[index] <= target) {
            // 要当前
            path.add(candidates[index]);
            backtrack(candidates, target - candidates[index], index, path, res);
            path.remove(path.size() - 1);
            // 不要当前
            backtrack(candidates, target, index + 1, path, res);

        } else {
            backtrack(candidates, target, index + 1, path, res);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Code0039().combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
