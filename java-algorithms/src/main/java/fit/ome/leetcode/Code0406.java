package fit.ome.leetcode;

/**
 * 根据身高重建队列
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 *
 * 对数组进行排序，保证每次插入位置，正确
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/24
 **/
public class Code0406 {
    public int[][] reconstructQueue(int[][] people) {

        int zeroIndex=0;
        for (int i = 0; i < people.length; i++) {
//            if(people[i])
        }
        return people;
    }
    private void swap(int[][] p,int i,int j){
        int[] t=p[i];
        p[i]=p[j];
        p[j]=t;
    }
}
