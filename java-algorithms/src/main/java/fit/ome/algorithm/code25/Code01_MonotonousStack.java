package fit.ome.algorithm.code25;

import java.util.Stack;

/**
 * 单调栈的实现
 * <p>
 * 求数组中，两侧的距离最近的最小边界
 */
public class Code01_MonotonousStack {
    /**
     * 通过单调栈实现
     * 简单实现，约束数组中的值不能相等
     *
     * @param arr
     * @return
     */
    // arr = [ 3, 1, 2, 3]
    //         0  1  2  3
    //  [
    //     0 : [-1,  1]
    //     1 : [-1, -1]
    //     2 : [ 1, -1]
    //     3 : [ 2, -1]
    //  ]
    public static int[][] getNearestLessRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 小于栈顶元素
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                Integer popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek(); // 左侧，最近的小于当前值的位置
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + ":[" + arr[i][0] + "," + arr[i][1] + "]");
        }
    }

    public static void main(String[] args) {
        printArr(getNearestLessRepeat(new int[]{3, 1, 2, 3}));
    }

}
