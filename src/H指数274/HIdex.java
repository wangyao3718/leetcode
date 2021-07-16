package H指数274;

import java.util.Arrays;

/**
 * @author wangyao
 * @date 2021-7-11 17:08
 * @description:
 *
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 *
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
 *
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 *
 *  
 *
 * 示例：
 *
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 *      由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *  
 *
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 *
 */
public class HIdex {
    public static void main(String[] args) {
    
        //官方题解：排序后二分
        int[] a = {3, 0, 1, 5, 6};
        //System.out.println(hIndx(a));
        
        //官方题解：计数排序
        //System.out.println(hIndx2(a));
    
        //二分
        System.out.println(hIndex3(a));
    }
    
    public static int hIndx(int[] b) {
        Arrays.sort(b);
        int i = b.length - 1;
        int h = 0;
        while (i >=0 && b[i] > h) {
            h++;
            i--;
        }
        return h;
    }
    
    public static int hIndx2(int[] b) {
        //h不可能大于论文数
        int n = b.length;
        int[] c = new int[n + 1];//每个元素表示引用i次的论文数
        for (int i = 0; i < b.length; i++) {
            if (b[i] >= n) {
                c[n]++;
            } else {
                c[b[i]]++;
            }
        }
        
        int tot = 0;
        for (int j = n; j >= 0; j--) {
            tot += c[j];
            if (tot >= j) {
                return j;
            }
        }
        return 0;
    }
    
    public static int hIndex3(int[] b) {
        int l = 0,r = b.length;
        while(l < r) {
            int mid = 1 + l + r >> 1;
            if (check(b, mid)) {
                l = mid;
            } else {
                r = mid -1;
            }
        }
        return r;
    }
    
    public static boolean check(int[] c, int mid) {
        int ans = 0;//满足条件的引用论文数
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= mid) {
                ans++;
            }
        }
        return ans >= mid;
    }
}
