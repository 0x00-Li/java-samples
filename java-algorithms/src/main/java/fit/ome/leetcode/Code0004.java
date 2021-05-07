package fit.ome.leetcode;

public class Code0004 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i=0;
        int j=0;
        int x=0;
        while (i<nums1.length&&j<nums2.length){
            res[x++]=nums1[i]>nums2[j]?nums2[j++]:nums1[i++];
        }
        for (int k = i; k < nums1.length; k++) {
            res[x++]=nums1[k];
        }
        for (int k = j; k < nums2.length; k++) {
            res[x++]=nums2[k];
        }
        if((res.length&1)==0){
            return (res[(res.length>>1)]+res[(res.length>>1)-1])/2.0d;
        }
        return res[(res.length>>1)];
    }

    public static void main(String[] args) {
        System.out.println(new Code0004().findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
    }
}
