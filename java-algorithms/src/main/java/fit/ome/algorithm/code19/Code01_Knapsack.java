package fit.ome.algorithm.code19;

/**
 * 背包能装下的最多价值
 * <p>
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code01_Knapsack {
    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {

        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int i, int bagRest) {
        if (i == w.length) {
            return 0;
        }
        if (bagRest < 0) {
            return -1;
        }
        int p1 = process(w, v, i + 1, bagRest);
        int p2 = 0;
        int next = process(w, v, i + 1, bagRest - w[i]);
        if (next != -1) {
            p2 = v[i] + next;
        }
        return Math.max(p1, p2);
    }
//
    // 改动态规划
    //
    public static int dp(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int index = w.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2=0;
                int next=rest-w[index]<0?-1:dp[index+1][rest-w[index]];
                if(next!=-1){
                    p2=next+v[index+1];
                }
                dp[index][rest]=Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }


}
