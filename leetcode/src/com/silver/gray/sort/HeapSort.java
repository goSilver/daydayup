package com.silver.gray.sort;

/**
 * 堆排序
 * @author csh
 * @date 2021/4/24
 */
public class HeapSort {

    /**
     * 堆排序（升序）
     * @param arr 待调整的堆
     */
    private void heapSort(int[] arr) {
        // 把无序数组构建成最大堆
        for (int i = (arr.length - 2) / 2; i > 0; i--) {
            downAdjust(arr, i, arr.length);
        }
        // 循环删除堆顶元素，移到集合末尾，调整堆产生新的堆顶
        for (int i = arr.length - 1; i > 0; i--) {
            // 最后一个元素和第一个元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 下沉调整最大堆
            downAdjust(arr, 0, i);
        }
    }

    /**
     * “下沉”调整
     *
     * @param arr         待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    private void downAdjust(int[] arr, int parentIndex, int length) {
        // temp保存父节点的值，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个字节点，直接跳出
            if (temp >= arr[childIndex]) break;

            // 无需真正交换，单向赋值即可
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = temp;
    }
}
