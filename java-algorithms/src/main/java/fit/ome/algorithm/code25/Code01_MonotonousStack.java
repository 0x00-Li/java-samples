package fit.ome.algorithm.code25;

import java.util.*;

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

    /**
     * 获取最近的小于值的位置
     * <p>
     * <p>
     * 数组中有重复值
     * <p>
     * 在单调栈中存储值的时候，使用链表数据结构
     * <p>
     * 在弹出恢复填充位置的时候，使用下部的链表的尾端作为最近小于的位置
     *
     * @param arr
     * @return
     */
    public static int[][] getNearLess(int[] arr) {

        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                // 小于的时候，需要栈顶弹出
                List<Integer> popList = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int j = 0; j < popList.size(); j++) {
                    int popIndex = popList.get(j);
                    res[popIndex][0] = leftIndex;
                    res[popIndex][1] = i;
                }

            }
            if (stack.isEmpty() || arr[i] > arr[stack.peek().get(0)]) {
                // 大于强制压栈
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else {
                // 等于，在链表追加
                stack.peek().add(i);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int j = 0; j < popList.size(); j++) {
                int popIndex = popList.get(j);
                res[popIndex][0] = leftIndex;
                res[popIndex][1] = -1;
            }
        }

        return res;
    }


    //for test

    /**
     * 纯暴力尝试
     *
     * @param arr
     * @return
     */
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            // 左侧最小
int leftIndex=-1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    leftIndex = j;
                    break;
                }
            }
            int rightIndex=-1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    rightIndex = j;
                    break;
                }
            }
            res[i][0]=leftIndex;
            res[i][1]=rightIndex;

        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i][0] != arr2[i][0] || arr1[i][1] != arr2[i][1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] generateNoRepeatArr(int maxVal, int maxSize) {
        int[] res = new int[randomMax(maxSize)];
        Set<Integer> s = new HashSet<>();
        while (s.size() != res.length) {
            s.add(randomMax(maxVal));
        }
        Iterator<Integer> iterator = s.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            res[index++] = iterator.next();
        }

        return res;
    }

    public static int randomMax(int max) {
        return (int) (Math.random() * max) + 1;
    }

    // for test
    public static int[] generateArr(int maxVal, int maxSize) {
        int[] res = new int[randomMax(maxSize)];
        for (int i = 0; i < res.length; i++) {
            res[i] = randomMax(maxVal);
        }
        return res;
    }

    //for test
    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + ":[" + arr[i][0] + "," + arr[i][1] + "]");
        }
    }
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }


    // for test
    public static void main(String[] args) {
//        int[] arr = {3, 1, 2, 3};
//        printArr(getNearestLessRepeat(arr));
//        printArr(rightWay(new int[]{5}));
        int maxSize=10;
        int maxVal=1000;
        int opTimes=10000;
        for (int i = 0; i < opTimes; i++) {
            int[] arr = generateNoRepeatArr(maxVal, maxSize);
            int[][] nor = getNearestLessRepeat(arr);
            int[][] right = rightWay(arr);
            if(!isEqual(nor, right)){
                System.out.println("Oops!!!");
                printArr(arr);
                System.out.println();
                printArr(nor);
                printArr(right);
                return;
            }
            arr=generateArr(maxVal,maxSize);
            if(!isEqual(getNearLess(arr),rightWay(arr))){
                System.out.println("repeat Oops");
                return;
            }
        }
        System.out.println("finish");
    }

}
