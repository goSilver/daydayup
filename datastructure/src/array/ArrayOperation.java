package array;

import java.util.Arrays;

/**
 * 常见的数组操作
 *
 * @author csh
 * @date 2019/9/26 9:08
 */
public class ArrayOperation {

    public static void main(String[] args) {
        // 定义一个二维数组，Java中可以定义不等长二维数组
        int[][] arr = new int[][]{{1, 2}, {3, 4, 5}, {6, 7, 8, 9}};
        // 普通for循环遍历
//        traversing(arr);
        // 增强型for循环遍历
//        traversingByForEach(arr);
        // 定义一个一维有序数组
        int[] orderArray = new int[]{1, 2, 3, 4, 5};
//        fill(orderArray, 8);
//        fill(orderArray, 2, 5, 9);

        // 定义一个一维无序数组
        int[] disorderArray = new int[]{3, 2, 6, 7, 5, 1, 4};
        sort(disorderArray);
    }

    /**
     * 遍历二维数组
     *
     * @param arr 传入数组
     */
    private static void traversing(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 增强型for循环遍历
     *
     * @param arr 传入数组
     */
    private static void traversingByForEach(int[][] arr) {
        int i = 0;
        for (int[] column : arr) {
            i++;
            int j = 0;
            for (int row : column) {
                j++;
                if (i <= arr.length && j <= column.length) {
                    System.out.print(row);
                }
            }
        }
    }

    /**
     * 将指定的int值分配给int型数组的每一个元素
     *
     * @param arr   数组
     * @param value int值
     */
    private static void fill(int[] arr, int value) {
        // 打印原始数组
        System.out.print("before: ");
        for (int i : arr) {
            System.out.print(i);
        }
        // 调用Arrays类的静态方法fill()
        Arrays.fill(arr, value);
        // 打印fill()操作过后的数组
        System.out.print("\nafter: ");
        for (int num : arr) {
            System.out.print(num);
        }
    }

    /**
     * 该方法将指定的int值分配给int型数组指定范围中的每个元素。
     * 填充的范围从索引fromIndex（包括）一直到索引toIndex（不包括）。如果fromIndex==toIndex，则填充范围为空。
     * 如果指定的索引位置大于或等于要进行填充的数组的长度，则会报出ArrayIndexOutOfBoundsException（数组越界异常）异常。
     *
     * @param arr       待填充的数组
     * @param fromIndex 起始索引
     * @param toIndex   结束索引
     * @param value     填充值
     */
    private static void fill(int[] arr, int fromIndex, int toIndex, int value) {
        // 打印原始数组
        System.out.print("before: ");
        for (int i : arr) {
            System.out.print(i);
        }
        // 调用Arrays类的静态方法fill()
        Arrays.fill(arr, fromIndex, toIndex, value);
        // 打印fill()操作过后的数组
        System.out.print("\nafter: ");
        for (int num : arr) {
            System.out.print(num);
        }
    }

    private static void sort(int[] arr) {
        // 打印原始数组
        System.out.print("before: ");
        for (int i : arr) {
            System.out.print(i);
        }
        // 调用Arrays类的静态方法sort()
        Arrays.sort(arr);
        // 打印fill()操作过后的数组
        System.out.print("\nafter: ");
        for (int num : arr) {
            System.out.print(num);
        }
    }
}
