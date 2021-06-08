package fit.ome.leetcode;

/**
 * 搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * 根据区间锁定范围
 */
public class Code0240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        while (i < m) {
            if (matrix[i][0] > target || matrix[i][n - 1] < target) {
                //起始值和尾值
                i++;
                continue;
            }
            if (matrix[i][0] == target || matrix[i][n - 1] == target) {
                return true;
            }
            j=1;
            while (j > 0 && j < n - 1) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] < target) {
                    j++;
                } else {
                    if (matrix[i][j - 1] < target) {
                        break;
                    } else {
                        j--;
                    }
                }
            }
            i++;
        }
        return false;
    }
}

