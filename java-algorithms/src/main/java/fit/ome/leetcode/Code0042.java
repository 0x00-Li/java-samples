package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 接雨水问题
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * <p>
 * <p>
 * 使用最大单调栈实现
 * <p>
 * 通过求最近最大边界进行，求解雨水面积
 * 求解，每个层次的接水量，然后求和
 */
public class Code0042 {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        // 栈中list结构，存放连续的想等值
        Stack<List<Node>> stack = new Stack<>();
        int i = 0;
        int sum = 0;
        while (i < height.length) {
            if (!stack.isEmpty() && stack.peek().get(0).v < height[i]) {
                while (!stack.isEmpty() && stack.peek().get(0).v < height[i]) {
                    // 所有小于当前值的都弹出
                    List<Node> pop = stack.pop();
                    if (!stack.isEmpty()) {
                        // 底层还有值，计算雨水量
                        List<Node> leftNode = stack.peek();
                        int min = Math.min(leftNode.get(0).v, height[i]);
                        sum += (min - pop.get(0).v) * (i - leftNode.get(leftNode.size() - 1).i - 1);
                    }
                }
                List<Node> list = new ArrayList<>();
                list.add(new Node(i, height[i]));
                stack.push(list);

            } else if (!stack.isEmpty() && stack.peek().get(0).v == height[i]) {
                // 连续相同，只在最后一个的时候进行雨水量
                stack.peek().add(new Node(i, height[i]));
            } else {
                List<Node> list = new ArrayList<>();
                list.add(new Node(i, height[i]));
                stack.push(list);
            }
            i++;
        }

        return sum;
    }

    public static class Node {
        int i;
        int v;

        public Node(int index, int val) {
            i = index;
            v = val;
        }
    }

    // 考虑动态规划解法
    // 构建左侧和右侧最大值辅助数组
    public int trap1(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int sum = 0;
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[height.length];
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i > 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for (int i = 1; i < height.length-1; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new Code0042().trap1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
