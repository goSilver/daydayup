package com.silver.leetbook.heap;

/**
 * 求topK个元素
 * 思路：
 * 取arr前k个元素构造一个k个元素的最小堆，
 * 然后从k开始遍历，并比较元素，如果大于堆顶元素，则去掉堆顶元素，并加入此元素
 * 执行一次堆化
 * 遍历到最后，这个最小堆剩下的就是topK
 *
 * 时间复杂度： O(NlogK)
 * 空间复杂度：O(K)
 *
 * @author csh
 * @date 2021/6/15
 **/
public class TopK2 {
    public static void main(String[] args) {
        TopK2 main = new TopK2();
        int[] sort = main.topK(new int[]{1, 3, 2, 5, 4}, 3);
        for (int i : sort) {
            System.out.print(i);
        }
    }
    private int[] topK(int[] arr, int k) {
        // 构造一个k个元素的大顶堆
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        for (int i = (int) Math.floor(k / 2); i >= 0; i--) {
            heapify(res, i, k);
        }

        // 向这个最大堆插入元素
        for (int i = k; i < arr.length; i++) {
            if (res[0] < arr[i]) {
                res[0] = arr[i];
                heapify(res, 0, k);
            }
        }
        return res;
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        if (left < len && arr[left] < arr[min])
            min = left;

        if (right < len && arr[right] < arr[min])
            min = right;

        if (min != i) {
            swap(arr, min, i);
            heapify(arr, min, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
