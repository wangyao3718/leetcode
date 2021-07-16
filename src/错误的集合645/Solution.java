package 错误的集合645;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyao
 * @date 2021-7-4 21:41
 * @description:
 * 集合s包含从1到n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合丢失了
 * 一个数字 并且 有一个数字重复 。
 *
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 *
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[1,2]
 */
public class Solution {
    public static void main(String[] args) {
        int[] a = {1, 2, 2, 4};
        
        //官方题解1：排序法
        //System.out.println(Arrays.toString(findErrorNums(a)));
        //官方题解2：哈希表
        //System.out.println(Arrays.toString(findErrorNums2(a)));
        //官方题解3：位运算
        System.out.println(Arrays.toString(findErrorNums3(a)));
    }
    
    /**
     * 官方题解1：排序法
     * @param nums
     * @return
     */
    public static  int[] findErrorNums(int[] nums) {
        int[] errorNums = new int[2];
        int n = nums.length;
        
        Arrays.sort(nums);
        
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            if (curr == prev) {
                errorNums[0] = prev;
            } else if (curr - prev > 1) {
                errorNums[1] = prev + 1;
            }
            prev = curr;
        }
        if (nums[n-1] != n) {
            errorNums[2] = n;
        }
        
        return errorNums;
    }
    
    /**
     * 官方题解2：哈希表
     * @param nums
     * @return
     */
    public static int[] findErrorNums2(int[] nums) {
        int[] errorNums = new int[2];
        int n = nums.length;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (int i = 1; i <= n; i++) {
            if (map.getOrDefault(i, 0) == 2) {
                errorNums[0] = i;
            } else if (map.getOrDefault(i ,0) == 0) {
                errorNums[1] = i;
            }
        }
        return errorNums;
    }
    
    /**
     * 官方题解3：位运算
     * @param nums
     * @return
     */
    public static int[] findErrorNums3(int[] nums) {
        int n = nums.length;
        
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        
        int lowbit = xor & (-xor);
        int num1 = 0,num2 = 0;
        for (int num : nums) {
            if ((lowbit & num) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((lowbit & i) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        
        return new int[]{num2, num1};
    }
}
