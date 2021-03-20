package fit.ome.algorithm.code01;

/**
 * 局部最小值问题
 * <br>
 * 无序数组
 * 任意两个位置的数不同
 * 定义：
 * [0]<[1] => [0]
 * [n-2]>[n-1] => [n-1]
 * [i-1]>[i]<[i+1] => [i]
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code09_BSAweSome {

    public static int aweSome(int[] arr) {
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int l = 1;
        int r = arr.length - 2;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (arr[m - 1] < arr[m]) {
                r = m - 1;
            } else if (arr[m + 1] < arr[m]) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return l;
    }

}
