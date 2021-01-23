package com.silver.tree;

/**
 * @author csh
 * @date 2021/1/23
 */
public class Sort {
    public static void main(String[] args) {

        int[] arr = new int[]{2, 6, 7, 8, 4, 1, 5};
        Sort sort = new Sort();

        sort.quickSort(arr, 0, 5);
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    /**
     * 递归版快速排序
     *
     * @param nums 待排序数组
     * @param lo   数组起始元素下标
     * @param hi   数组结束元素下标
     */
    private void quickSort(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        // 通过交换元素构建分界点 p
        int p = partition(nums, lo, hi);

        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    /**
     * 分治法，对数组以分界点（默认取数组第一位元素）进行分区
     *
     * @param list 待分区数组
     * @param low  数组起始元素下标
     * @param high 数组结束元素下标
     * @return
     */
    private int partition(int[] list, int low, int high) {
        // 数组的第一个作为中轴
        int tmp = list[low];
        while (low < high) {
            while (low < high && list[high] > tmp) {
                high--;
            }
            // 比中轴小的记录移到低端
            list[low] = list[high];
            while (low < high && list[low] < tmp) {
                low++;
            }
            // 比中轴大的记录移到高端
            list[high] = list[low];
        }
        // 中轴记录到尾
        list[low] = tmp;
        // 返回中轴的位置
        return low;
    }
}
