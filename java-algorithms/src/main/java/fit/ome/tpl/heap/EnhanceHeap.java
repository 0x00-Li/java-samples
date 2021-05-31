package fit.ome.tpl.heap;

import fit.ome.leetcode.Code0347;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆
 */
public class EnhanceHeap<T> {

    ArrayList<T> heap;// 使用动态数组替代静态数组，可以不限制初始化长度
    Map<T, Integer> indexedMap;// 返乡索引，存储对象相关的在堆数组中的真实位置
    int heapSize;// 堆的真实大小
    Comparator<T> comparator;// 接收外部的比较器

    public EnhanceHeap(Comparator comparator) {
        heap = new ArrayList<>();
        indexedMap = new HashMap<>();
        this.comparator = comparator;
    }

    public int size() {
        return heapSize;
    }

    public void push(T t) {
        heap.add(t);
        indexedMap.put(t, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 执行向上调整
     */
    public void heapInsert(int index) {
        int pIndex = (index - 1) >> 2;
        if (comparator.compare(heap.get(pIndex), heap.get(index)) > 0) {
            swap(pIndex, index);
            index = pIndex;
            pIndex = (index - 1) >> 1;
        }

    }

    /**
     * 向下调整
     *
     * @param index
     */
    public void heapfiy(int index) {
        int left = (index << 1);
        while (left < heapSize) {
            int minIndex = left + 1 < heapSize ?
                    (comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ? left : 1 + left) :
                    (comparator.compare(heap.get(left), heap.get(index)) < 0 ? left : index);
            if (minIndex == index) {
                break;
            }
            swap(minIndex, index);
            index = minIndex;
            left = index << 1;
        }

    }

    public void resign(T t) {
        heapfiy(indexedMap.get(t));
        heapInsert(indexedMap.get(t));
    }


    public void swap(int i, int j) {
        T t = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, t);

        indexedMap.put(heap.get(i), i);
        indexedMap.put(heap.get(j), j);
    }

    public T pop() {
        T t = heap.get(0);
        swap(0, heapSize - 1);
        indexedMap.remove(t);
        heap.remove(heapSize--);
        heapfiy(0);
        return t;
    }

}
