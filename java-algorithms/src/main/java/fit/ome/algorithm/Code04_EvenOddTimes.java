package fit.ome.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个数组中，只有一个数出现了奇数次，其他的都是偶数次
 * <br>
 * 在一个数组中，数m出现了奇数次n，其他数据都出现了偶数次，求出这个数
 * 1. n>0
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/16
 **/
public class Code04_EvenOddTimes {
    public static int oddTimeVal(int[] arr) {
        //1. n^n=0
        //2. n^0=n
        // 所有出现偶数次的数，在进行异或操作后，会成为0
        // 出现奇数次的数字，就可以进行异或操作，得出自身
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        return result;
    }

    public static int oddTimesHash(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i :
                arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }

        }
        for (int i : map.keySet()) {
            if (map.get(i) % 2 != 0) {
                return i;
            }
        }
        return -1;
    }


    public static void oddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & (-eor);// 最有的1
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne);
        System.out.println(eor^onlyOne);
    }

    public static void main(String[] args) {
        System.out.println(oddTimeVal(new int[]{1,2,3,4,4,3,2}));
        oddTimesNum2(new int[]{3,5,6,7,8,7,6,5});

    }
}
