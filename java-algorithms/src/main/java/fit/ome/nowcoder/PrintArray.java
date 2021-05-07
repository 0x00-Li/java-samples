package fit.ome.nowcoder;

/**
 * 打印二维数组
 * <p>
 * 转圈打印
 */
public class PrintArray {

    public static void printArr(int[][] arr) {
        int startX = 0;
        int startY = 0;
        int endX = arr.length - 1;
        int endY = arr[0].length - 1;

        while (startX <= endX && startY <= endY) {
            for (int i = startY; i <= endY; i++) {
                System.out.print(arr[startX][i]+",");
            }
            for (int i = startX+1; i <= endX; i++) {
                System.out.print(arr[i][endY]+",");
            }
            for (int i = endY-1; i >=startY ; i--) {
                System.out.print(arr[endX][i]+",");
            }
            for (int i = endX-1; i >startX ; i--) {
                System.out.print(arr[i][startY]+",");
            }
            startX++;
            startY++;
            endX--;
            endY--;
        }

    }

    public static void main(String[] args) {
        printArr(new int[][]{{1,2,3},{4,5,6},{7,8,9 }});
    }
}
