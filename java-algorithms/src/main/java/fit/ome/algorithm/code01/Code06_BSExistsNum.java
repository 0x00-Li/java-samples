package fit.ome.algorithm.code01;

/**
 * 在有序数组中判断是否存在某个数
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/20
 **/
public class Code06_BSExistsNum {
    public static boolean exists(int[] arr, int num) {

        return find(arr, 0, arr.length - 1, num);

    }

    /**
     * 锁求范围是左右包含区间
     *
     * @param arr
     * @param left
     * @param right
     * @param num
     * @return
     */
    public static boolean find(int[] arr, int left, int right, int num) {
        if (left > right) {
            return false;
        }
        int mid = left + right >> 1;
        if (arr[mid] == num) {
            return true;
        }
        if (arr[mid] < num) {
            return find(arr, mid + 1, right, num);
        } else {
            return find(arr, left, mid - 1, num);
        }
    }

    public static boolean exist02(int[] sortedArr,int num){
        int l=0;
        int r=sortedArr.length-1;

        while (l<r){
            int mid=l+r>>1;
            if(sortedArr[mid]==num){
                return true;
            }
            if(sortedArr[mid]<num){
                l=mid+1;
            }else {
                r=mid-1;
            }
        }
        return false;
    }

//    private static int[] generatedRandomArray(int maxSize, int maxValue,int notExists) {
//        int start = (int) Math.random() * (maxValue + 1);
//        int size = (int) (Math.random() * (maxSize + 1));
//
//        for (int i = 0; i < size; i++) {
//
//        }
//    }

    public static void main(String[] args) {
        System.out.println(exists(new int[]{4,6,7,9,10,29,89},90));
        System.out.println(exist02(new int[]{4,6,7,9,10,29,89},90));
    }
}
