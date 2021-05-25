package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 * 从前往后放入数据
 */
public class Code0406_1 {
    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    // 身高不同，高的在前
                    return o2[0] - o1[0];
                } else {
                    // 相同身高的，前面人数多的在后
                    return o1[1] - o2[1];
                }

            }
        });
        List<int[]> res = new ArrayList<>();
        for (int[] p : people) {
//            先加入大身高且前面人数少的数据
            // 次身高到来的时候，会根据自己前面的人数，进行添加，之前加入的位置，会被后移
            res.add(p[1], p);
        }
        return res.toArray(new int[res.size()][]);
    }

}
