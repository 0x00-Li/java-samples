package fit.ome.leetcode;



import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍3
 * https://leetcode-cn.com/problems/house-robber-iii/
 * <p>
 * 贪心算法
 * <p>
 * 傻缓存
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/6/1
 **/
public class Code0337 {
    public int rob(TreeNode root) {
        // Node相关  0 > 不要的值  1> 要的值
        Map<TreeNode, Integer[]> map = new HashMap<>();
        return pre(root, false, map);
    }

    public int pre(TreeNode node, boolean isGet, Map<TreeNode, Integer[]> map) {
        if (node == null) {
            return 0;
        }

        int total1 = 0;
        int total2 = 0;
        int total3 = 0;
        Integer[] val = map.get(node);
        if (val == null) {
            val = new Integer[]{-1, -1};
            map.put(node, val);
        }
        if (isGet) {

            if (val[0] == -1) {
                // 不能要当前节点
                total1 += pre(node.left, false, map);
                total1 += pre(node.right, false, map);
                val[0] = total1;
            }else {
                total1=val[0];
            }

        } else {
            // 可要
            if (val[1] == -1) {
                total2 += node.val;
                total2 += pre(node.left, true, map);
                total2 += pre(node.right, true, map);
                val[1] = total2;
            }else {
                total2=val[1];
            }

            // 可不要
            if (val[0] == -1) {
                total3 += pre(node.left, false,map);
                total3 += pre(node.right, false,map);
                val[0] = total3;
            }
            else {
                total3=val[0];
            }
        }
        return Math.max(total1, Math.max(total2, total3));
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
