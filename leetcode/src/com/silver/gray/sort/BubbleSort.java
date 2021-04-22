package com.silver.gray.sort;

/**
 * @author csh
 * @date 2021/4/22
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort main = new BubbleSort();
        int[] arr = new int[]{1, 3, 6, 5, 2, 4, 8, 7};
        main.sortSecond(arr);
        for (int num : arr) {
            System.out.print(num);
        }
    }

    /**
     * 暴力版冒泡
     * 外层循环控制所有的回合
     * 内层循环实现每一轮的冒泡处理
     * 先进行元素比较，再进行元素交换
     * <p>
     * 时间复杂度：O(n^2)
     *
     * @param arr 数组
     */
    private void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 注意，这里边界可以是length-1，优化一下也可以是length-i-1
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 注意
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡的优化第一版
     * 如果能判断出数列已经有序，并做出标记，那么剩下的几轮排序就不必执行了
     *
     * @param arr 数组
     */
    private void sortSecond(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 是否有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    // 发生元素交换，则说明数组无序，标志位变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 记录无序边界的冒泡
     *
     * @param arr
     */
    private void sortThird(int[] arr) {
        // 记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数组的边界，每次比较只需要比到这里为止
        int sortedOrder = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            // 是否有序标识，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortedOrder; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    // 发生元素交换，说明数组无序，标记变为false
                    isSorted = false;
                    // 更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            // 更新边界
            sortedOrder = lastExchangeIndex;
            if (isSorted) break;
        }
    }
}
