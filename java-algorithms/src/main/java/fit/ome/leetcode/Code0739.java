package fit.ome.leetcode;

/**
 * 每日温度
 * <p>
 * 动态规划
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/11
 **/
public class Code0739 {
    public int[] dailyTemperatures(int[] temperatures) {
// 最高气温辅助数组
        int[] maxArr = new int[temperatures.length];
        int[] res = new int[temperatures.length];
        maxArr[temperatures.length - 1] = temperatures.length - 1;
        res[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            if (temperatures[i] >= temperatures[i + 1]) {

                if (maxArr[i + 1] == i + 1) {
                    maxArr[i] = i;
                    res[i] = 0;
                } else {
                    // 辅助数组的索引
                    int index = i + 1;
                    while (index < temperatures.length-1) {
                        if (temperatures[i] < temperatures[maxArr[index]]) {
                            maxArr[i] = maxArr[index];
                            res[i] = maxArr[i] - i;
                            break;
                        }
                        if(index==maxArr[index]){
                            break;
                        }
                        index=maxArr[index];
                    }
                }

            } else {
                maxArr[i] = i + 1;
                res[i] = 1;
            }

        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new Code0739().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
//        System.out.println(new Code0739().dailyTemperatures(new int[]{55,38,53,81,61,93,97,32,43,78}));
        System.out.println(new Code0739().dailyTemperatures(new int[]{34,80,80,34,34,80,80,80,80,34}));

    }
}
