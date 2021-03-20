package fit.ome.algorithm.code01;

import java.util.Arrays;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code07_BSNearLeft {
    public static int nearestLeft(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0 || sortedArr[0] > num || sortedArr[sortedArr.length - 1] < num) {
            return -1;
        }
        int l = 0;
        int r = sortedArr.length - 1;
        int mid = 0;
        while (l < r) {
            mid = l + r >> 1;
            if (sortedArr[mid] < num) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public static int nearestLeft02(int[] arr,int num){
        int l=0;
        int r=arr.length-1;
        int index=-1;
        while (l<=r){
            int m=l+((r-l)>>1);
            if(arr[m]>=num){
                index=m;
                r=m-1;
            }else {
                l=m+1;
            }
        }
        return index;
    }

    public static int test(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0 || sortedArr[0] > num || sortedArr[sortedArr.length - 1] < num) {
            return -1;
        }
        for (int i = 0; i < sortedArr.length; i++) {
            if (sortedArr[i] >= num) {
                return i;
            }
        }
        return -1;
    }

    // 对数器
    public static int[] generatedRandomArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " , ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        System.out.println(nearestLeft(new int[]{831},497));
        int times = 10000;
        int maxValue = 1000;
        int maxSize = 10;
        System.out.println("begin");
        for (int i = 0; i < times; i++) {
            int[] arr = generatedRandomArr(maxSize, maxValue);
            Arrays.sort(arr);

            int v = (int) (Math.random() * (maxValue + 1));
            int test = test(arr, v);
            int nearestLeft = nearestLeft(arr, v);
            int nearestLeft02 = nearestLeft02(arr, v);
            if (test != nearestLeft||test!=nearestLeft02) {
                printArr(arr);
                System.out.println(v);
                System.out.println(test);
                System.out.println(nearestLeft);
                System.out.println(nearestLeft02);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish");
    }
}
