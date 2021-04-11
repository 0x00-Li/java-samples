package fit.ome.algorithm.code07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 增强堆
 *
 * <br>
 * <p>
 * 1.灵活接受比较器
 * 2. 增加反向索引：指定值在对应堆的位置
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/11
 **/
public class EnhanceHeap<T> {
    // 使用arraylist 代替堆数组
    private ArrayList<T> heap;
    // 反向索引
    // key 指定对象， value 堆中的真实位置
    private HashMap<T, Integer> indexedMap;
    private int heapSize;
    private Comparator<T> comparator;

    public EnhanceHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        heap = new ArrayList<>();
        heapSize = 0;
        indexedMap = new HashMap<>();
    }

    public boolean contain(T t) {
        return indexedMap.containsKey(t);
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T t) {
        heap.add(t);
        indexedMap.put(t, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 向上调整
     *
     * @param index
     */
    private void heapInsert(int index) {
        // 和系统保持一致，维持小根堆现状
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T oi = heap.get(i);
        T oj = heap.get(j);
        heap.set(i, oj);
        heap.set(j, oi);
        // 维护反向索引
        indexedMap.put(oi, j);
        indexedMap.put(oj, i);
    }

    public T pop() {
        T t = heap.get(0);
        swap(0, heapSize - 1);
        // 维护索引
        indexedMap.remove(t);
        heap.remove(--heapSize);
        heapify(0);
        return t;
    }

    /**
     * 向下调整
     */
    public void heapify(int index) {
        int left = (index << 1) + 1;
        while (left < heapSize) {
            int minIndex = (left + 1) < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            minIndex = comparator.compare(heap.get(minIndex), heap.get(index)) < 0 ? minIndex : index;
            if (index == minIndex) {
                // 不需要调整
                break;
            }
            swap(minIndex, index);
            index = minIndex;
            left = (index << 1) + 1;
        }
    }

    public void remove(T t) {
        T replace = heap.get(heapSize - 1);
        Integer index = indexedMap.get(t);
        indexedMap.remove(t);
        heap.remove(--heapSize);
        if (t != replace) {
            heap.set(index, replace);
            indexedMap.put(replace, index);
            resign(replace);
        }
    }

    /**
     * 重新调整堆
     *
     * @param t
     */
    public void resign(T t) {
        heapInsert(indexedMap.get(t));
        heapify(indexedMap.get(t));
    }

    public List<T> getAll() {
        List<T> ans = new ArrayList<>();
        ans.addAll(heap);
        return ans;
    }
}
