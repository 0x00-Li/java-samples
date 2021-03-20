package fit.ome.algorithm.code01;

/**
 * 在一个数组中，某个数出现了k次，其他的数都出现了m次，k>1 ,k<m
 * 输出此数
 * <br>
 * 要求：空间复杂度 O(1)
 * 时间复杂度O(n)
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/16
 **/
public class Code05_OnlyKTimes {

    public static int kTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        for (int n : arr) {
            for (int i = 0; i < 32; i++) {
                t[i] += (n >> i) & 1;
            }
        }
// k> 1
// k<m
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m == 0) {
                continue;
            }
            if (t[i] % m == k) {
                ans |= (1 << i);
            } else {
                return -1;
            }

        }
        if (ans == 0) {
            int count = 0;
            for (int n : arr) {
                if (n == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(kTimes(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5}, 2, 3));
    }
}
