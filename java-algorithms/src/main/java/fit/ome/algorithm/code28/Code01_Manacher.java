package fit.ome.algorithm.code28;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/12
 **/
public class Code01_Manacher {
    // 最大回文字串长度
    public static int manacher(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherStr(s.toCharArray());
        int max = Integer.MIN_VALUE;
        int C = -1;// 获取最大回文半径的最右边界的时候，的中心点位置
        int R = -1;// 最大回文半径的最右边界,不包含回文最后一位字符串
        int[] pArr = new int[str.length];// 回文半径数组
        for (int i = 0; i < str.length; i++) {
            // 起初，R<i 是违反规则的，独立处理
            // 当前位置的回文半径初始化逻辑：
            //      当前位置和中心点对称过去 的位置2*C-i 和 回文最右侧边界减去当前位置的距离R-i 取最小
            //      就是在pArr[2*C-1]和R-i中取最小作为当前的回文半径
            //          选择覆盖了 对称点的回文半径可能大于C的回文半径，此时最多取R-i
            //          如果 对称点的回文半径也在C的覆盖范围内，则取，对称点的回文半径
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 判断当前点的回文半径是否需要扩张,在左右不越界 的情况下尝试扩张
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 更新回文最右边界的中心点和最右边界
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);

        }
        return max;
    }

    public static char[] manacherStr(char[] s) {
        // 前后，以及每个字符串的间隔填充
        // 新字符串总长度会成为2n+1
        char[] ns = new char[2 * s.length + 1];
        int index = 0;
        for (int i = 0; i < ns.length; i++) {
            // 填充后的结果，奇数位置都是间隔字符串
            ns[i] = (i & 1) == 0 ? '#' : s[index++];
        }
        return ns;
    }
}
