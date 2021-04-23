package fit.ome.algorithm.code26;

/**
 * 斐波那契数列实现
 */
public class Code01_FibonacciProblem {
    // 暴力实现 O(N)
    // 递归实现
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    // 迭代实现
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;

    }


    // 优化实现O(logN)
    // for test
    public static void main(String[] args) {
        System.out.println(f1(6));
        System.out.println(f2(6));
    }
}
