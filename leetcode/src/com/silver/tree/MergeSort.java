package com.silver.tree;

/**
 * 根据遍历树的思想实现归并排序
 *
 * @author csh
 * @date 2021/1/23
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 6, 7, 8, 4, 1, 5, 3, 9};
        MergeSort sort = new MergeSort();
        sort.mergeSort(arr, 0, 8);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * 快速排序
     *
     * @param arr 无序数组
     * @param lo  数组起始元素下标
     * @param hi  数组结束元素下标
     */
    private void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);

        merge(arr, lo, mid, hi);
    }

    /**
     * 合并两个有序数组
     *
     * @param arr 有序数组
     * @param lo  左数组起始节点下标
     * @param mid 分界点元素下标
     * @param hi  末尾元素下标
     */
    private void merge(int[] arr, int lo, int mid, int hi) {

        // 临时数组
        int[] tempArr = new int[arr.length];
        // 记录遍历下标
        int index = lo;
        // 右侧数组起始元素下标
        int beginRight = mid + 1;
        int temp = lo;
        while (lo <= mid && beginRight <= hi) {
            if (arr[lo] <= arr[beginRight]) {
                tempArr[index++] = arr[lo++];
            } else {
                tempArr[index++] = arr[beginRight++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (beginRight <= hi) {
            tempArr[index++] = arr[beginRight++];
        }
        while (lo <= mid) {
            tempArr[index++] = arr[lo++];
        }

        // 拷贝结果
        while (temp <= hi) {
            arr[temp] = tempArr[temp++];
        }
    }

}
