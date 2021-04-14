package fit.ome.algorithm.code07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/11
 **/
public class Code01_CoverMax {
    /**
     * 暴力解，线段最小单位1，循环判断每个区间的重复次数，返回最大
     *
     * @param lines
     * @return
     */
    public static int coverMax1(int[][] lines) {
// 假设初始化，没重复的
        int ans = 0;

        // 明确线段最小开始为止和最大开始为止
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        // 因为线段的最小单位是1，设置轮训访问是0.5
        for (double i = min + 0.5; i < max; i++) {
            int cover = 0;
            for (int j = 0; j < lines.length; j++) {
                if (i < lines[j][1] && i > lines[j][0]) {
                    cover++;
                }
            }
            ans = Math.max(cover, ans);
        }

        return ans;
    }
    //-------------------

    /**
     * 优化解
     * <p>
     * 1.将线段按照起始位置排序
     * 2.遍历所有线段，
     * 2.1、 遍历线段的尾的小根堆，如果线段的结尾在某个线段开始位置之前，就抛弃当前为尾的线段
     * 2.2、 然后将线段加入小根堆中
     * 2.3、 在循环的过程中
     *
     * @param arr
     * @return
     */
    public static int coverMax2(int[][] arr) {
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        // 按照起始位置排序
        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.s - o2.s;
            }
        });
        PriorityQueue<Integer> endHeap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {

            while (!endHeap.isEmpty() && endHeap.peek() <= lines[i].s) {
                endHeap.poll();
            }
            // 需要先把当前线段的尾端加入，尾位置堆
            endHeap.add(lines[i].e);
            max = Math.max(max, endHeap.size());
        }
        return max;
    }

    public static class Line {
        int s;
        int e;

        public Line(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    // for test
    public static int[][] generateLineArr(int start, int end, int maxSize) {
        int[][] arr = new int[maxRandom(maxSize)][2];
        for (int i = 0; i < arr.length; i++) {
            int a = start + (int) (Math.random() * (end - start + 1));
            int b = start + (int) (Math.random() * (end - start + 1));
            if (a == b) {
                b = a + 1;
            }
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }
        return arr;
    }

    public static int maxRandom(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void main(String[] args) {
        int maxSize = 200;
        int start = 0;
        int end = 1000;
        int opTimes = 10000;
        for (int i = 0; i < opTimes; i++) {
            int[][] arr = generateLineArr(start, end, maxSize);
            int max1 = coverMax1(arr);
            int max2 = coverMax2(arr);
            if (max1 != max2) {
                System.out.println("Oops!!!");
                break;
            }
        }
        System.out.println("Finish");
    }
}
