package fit.ome.algorithm.code19;

/**
 * 从左到右的尝试模型
 * <p>
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {
    // str 中只包含0~9的数字
    public static int number(String str) {
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }

        if (str[i] == '0') {
            return 0;
        }
        int ways = process(str, i + 1);
        if (i + 2 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    // ==
//    改动态规划
    public static int number_dp(String str) {
        char[] chars = str.toCharArray();
        // i 字符之前有多少种
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            int ways = dp[i + 1];
            if (i + 1 < chars.length && (chars[i] - '0') * 10 + chars[i + 1] - '0' < 27) {
                ways += dp[i + 2];
            }
            dp[i] = ways;
        }
        return dp[0];
    }
}
