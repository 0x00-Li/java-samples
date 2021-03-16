package fit.ome;

import fit.ome.sort.OmeSort;
import fit.ome.sort.impl.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        int[] nums=new int[3];
        OmeSort os = new Sort_1_Bubble();
        int[] nums = {17, 5, 3, 1, 3, 7, 8, 5, 2, 0, 4};// length=10

        // ----
//        swap(nums,0,1);
//        printArray(nums);
        printArray(os.sort(nums.clone()));
        os = new Sort_2_Selection();
        printArray(os.sort(nums.clone()));
        os = new Sort_3_Insertion();
        printArray(os.sort(nums.clone()));
        os = new Sort_4_Shell();
        printArray(os.sort(nums.clone()));
        System.out.println("------merge------");
        os = new Sort_5_Merge();
        printArray(os.sort(nums.clone()));
        System.out.println("------quick------");
        // quick
        // =1
        // partitionIndex=11 nums={ 4,5, 3, 1, 3, 7, 8, 5, 2, 0, 17}
        // partitionIndex=5   nums={5,}
        os = new Sort_6_Quick();
        printArray(os.sort(nums.clone()));
        System.out.println("------heap------");
        os = new Sort_7_Heap();
        printArray(os.sort(nums.clone()));
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int t : arr) {
            System.out.print(t);
            System.out.print(",");
        }
        System.out.println("]");
    }
    private static void swap(int[] arr,int i,int j){
        arr[i]=arr[i]+arr[j];
        arr[j]=arr[i]-arr[j];
        arr[i]=arr[i]-arr[j];
    }
}
