package 和相同的二元数组930;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyao
 * @date 2021-7-8 10:14
 * @description:
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 *
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        int goal = 2;
        
        //个人解：超时
        //System.out.println(numSubArrayWithSum(nums, goal));
        
        //官方解：滑动窗口
        //System.out.println(numSubArrayWithSum2(nums, goal));
        
        //官方解：哈希表
        System.out.println(numSubArrayWithSum3(nums, goal));
    }
    
    public static int numSubArrayWithSum(int[] nums, int goal) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j ++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum == goal) {
                    count += 1;
                }
            }
        }
        return count;
    }
    
    public static int numSubArrayWithSum2(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0,left2 = 0, right = 0;
        int sum1 = 0,sum2 = 0;
        int ret = 0;
        while(right < n) {
            sum1 += nums[right];
            while(left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            
            sum2 += nums[right];
            while(left2 <=right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
         }
         return ret;
    }
    
    public static int numSubArrayWithSum3(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0,ret = 0;
        for (int num : nums) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += map.getOrDefault(sum -goal, 0);
        }
        return ret;
    }
}
