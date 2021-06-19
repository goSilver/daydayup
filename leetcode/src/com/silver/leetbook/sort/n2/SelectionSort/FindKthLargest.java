package com.silver.leetbook.sort.n2.SelectionSort;

/**
 * 数组中第k大个数
 * 思路：
 * 利用选择排序，把最大元素排到前面，循环k次即可
 * @author csh
 * @date 2021/6/19
 **/
public class FindKthLargest {

    public static void main(String[] args) {
        FindKthLargest main = new FindKthLargest();
        int[] arr = new int[]{1, 3, 2, 4, 5};
        int res = main.findKthLargest(arr, 1);
        System.out.print(res);
    }

    public int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[maxIndex])
                    maxIndex = j;
            }
            swap(nums, i, maxIndex);
        }
        return nums[k - 1];
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
