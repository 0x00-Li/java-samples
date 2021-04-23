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
// f(n)=f(n-1)+f(n-2)
    // 1,1,2,3,5,8
    // f(3)=f(2)+f(1)=1+1=2
    // f(4)=f(3)+f(2)=3+2=5
    // 第n个值依赖前前面的两个值，理解为n阶的菲波那切数列
    // 同理求解|f(n),f(n-1)|=|f(1),f(2)| * [[a,b],[c,d]]^(n-2)
    // 求出abcd 然后求出
    // 根据已知的数据项：求解abcd的值
    // n=3 代入
    // a+b=2
    // c+d=1
    // n-4 代入
    // 2a+b=3
    // 2c+d=2
    // 求解：a=1,b=1,c=1,d=0

    /**
     * 优化实现
     * <p>
     * 通过列项式和矩阵的乘积求解
     *
     * @return
     */
    public static int matrixFibonacci(int n) {
        if (n < 1) {
            return 0;

        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] matrix = new int[][]{
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPow(matrix, n - 2);
        return res[0][0]+res[1][0];
    }

    /**
     * 矩阵的n阶指数
     *
     * @param matrix
     * @param n      指数值
     */
    private static int[][] matrixPow(int[][] matrix, int n) {
        // 构建单值矩阵
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            res[i][i] = 1;
        }

        //  矩阵的1次方
        int[][] t = matrix;
        for (; n != 0; n >>= 1) {
            // 判断当前这个矩阵的次方是否保留
            if ((n & 1) != 0) {

                res = multiMatrix(res, t);
            }
            // 推进矩阵的次方数
            t = multiMatrix(t, t);
        }
        return res;
    }

    /**
     * 两个矩阵相乘返回
     *
     * @param m1
     * @param m2
     * @return
     */
    private static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        System.out.println(f1(6));
        System.out.println(f2(6));
        System.out.println(matrixFibonacci(6));
        int n = 5;
        n >>= 1;
        System.out.println(n);
    }
}
