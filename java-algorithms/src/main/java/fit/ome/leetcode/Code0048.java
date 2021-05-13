package fit.ome.leetcode;

/**
 * 旋转图形
 * <p>
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class Code0048 {

    public void rotate(int[][] matrix) {
        if (matrix.length == 1) {
            return;
        }
        // 待填充当前点的，目标点坐标
        // mp[i][j]=mp[n-j-1][i];
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < (len + 1) / 2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i]=matrix[len-i-1][len-j-1];
                matrix[len-i-1][len-j-1]=matrix[j][len-i-1];
                matrix[j][len-i-1]=t;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = new int[5][5];//{new int[]{1, 2, 3,4, 5,}, new int[]{ 6,7,8,9,10}, new int[]{7,8,9}};
        int v = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = v++;
            }
        }
//        int[][] matrix = {new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7,8,9}};
        new Code0048().rotate(matrix);
        System.out.println();
    }

}
