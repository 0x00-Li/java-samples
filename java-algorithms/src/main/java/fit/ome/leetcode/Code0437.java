package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * https://leetcode-cn.com/problems/path-sum-iii/
 * <p>
 * 回溯+前缀和
 */
public class Code0437 {


    public int pathSum(TreeNode root, int targetSum) {
        // 前缀和map
        Map<Integer, Integer> preMap = new HashMap<>();
        preMap.put(0, 1);
        return pre(root, targetSum, 0, preMap);

    }

    private int pre(TreeNode node, int targetSum, int preSum, Map<Integer, Integer> preMap) {
        if (node == null) {
            return 0;
        }
        int currSum = preSum + node.val;
        int ans = 0;
        ans += preMap.getOrDefault(currSum - targetSum, 0);
        preMap.put(currSum, preMap.getOrDefault(preSum + node.val, 0) + 1);


        ans += pre(node.left, targetSum, currSum, preMap);
        ans += pre(node.right, targetSum, currSum, preMap);

        preMap.put(currSum, preMap.getOrDefault(currSum, 0) - 1);

        return ans;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
