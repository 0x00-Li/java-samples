package fit.ome.little;

/**
 * 提取出一个整数的最右侧的数字
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/16
 **/
public class Code01_RightestOne {


    public static int rightest1(int x) {
        return x & (~x + 1);
    }

    // -x =~x+1
    public static int rightest2(int x) {
        return x & (-x);
    }
}
