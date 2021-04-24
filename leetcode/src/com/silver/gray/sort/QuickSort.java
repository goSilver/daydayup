package com.silver.gray.sort;

/**
 * 快速排序
 *
 * @author csh
 * @date 2021/4/24
 */
public class QuickSort {
    private void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大于或等于endIndex
        if (startIndex > endIndex) return;
        // 得到基准元素位置
        int pivotIndex = partitionDouble(arr, startIndex, endIndex);
        // 根据基准元素，分成两部分进行递归
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 分治（双边循环法）
     *
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @return 基准元素位置
     */
    private int partitionDouble(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置（也可以是随机位置）的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            // 控制right指针比较并左移
            while (left < right && arr[right] > pivot) {
                right--;
            }
            // 控制left指针比较并右移
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // 交换left和right指针所指向的元素
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // pivot和指针重合点交换
        arr[startIndex] = arr[left];
        arr[left] = pivot;
        return left;
    }

    /**
     * 分治（单边循环法）
     *
     * @param arr   待交换元素位置
     * @param start 起始下标
     * @param end   结束下标
     * @return 基准元素位置
     */
    private int partitionSingle(int[] arr, int start, int end) {
        // 取第一个位置（也可以是随机位置）的元素作为基准元素
        int pivot = arr[start];
        int mark = start;

        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < pivot) {
                mark++;
                int temp = arr[mark];
                arr[mark] = arr[i];
                arr[i] = temp;
            }
        }
        arr[start] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }
}
