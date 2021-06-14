package com.silver.leetbook.heap;

/**
 * 堆排模板
 *
 * @author csh
 * @date 2021/6/14
 **/
public class HeapSort {
    public static void heapSort(int[] arr) {
        // 1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        // 然后继续调整堆，再将堆顶元素与末尾元素交换，得到第二大元素。如此反复进行交换、重建、交换。
        // 2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上, 也就是说只调用一次,并没有得到大顶堆）
     * 就是将arr[i] 的值放到本次 调整过程中适当的位置。
     *
     * @param arr    : 数组
     * @param i      : 非叶子节点的索引
     * @param length : 对多少个元素进行调整，这个length是逐渐减少的..
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2*i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];//把较大的值，赋给当前节点
                i = k;//i 指向k,继续循环比较
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
