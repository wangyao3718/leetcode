package 原子的数量726;

import java.util.*;

/**
 * @author wangyao
 * @date 2021-7-5 11:10
 * @description:
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 *
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 *
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 *
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 *
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 *
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 *
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2:
 *
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3:
 *
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-atoms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountOfAtom {
    int i,n;
    String formula;
    
    public static void main(String[] args) {
        //官方题解：栈+哈希表
        System.out.println(new CountOfAtom().countOfAtom());
    }
    
    public String countOfAtom() {
        this.i = 0;
        this.formula = "K4(ON(SO3)2)2";
        this.n = this.formula.length();
            
        Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
        stack.push(new HashMap<String, Integer>());
        
        int n = formula.length();
        while (i< n) {
            char curr = formula.charAt(i);
            if (curr == '(') {
                stack.push(new HashMap<String, Integer>());
                i++;
            } else if (curr == ')') {
                i++;
                int groupNum = parseNum();
                Map<String, Integer> latestAtomGroupMap = stack.pop();
                Map<String, Integer> countMap = stack.peek();
                for (Map.Entry<String, Integer> entry : latestAtomGroupMap.entrySet()) {
                    String atom = entry.getKey();
                    int num = entry.getValue();
                    countMap.put(atom, countMap.getOrDefault(atom, 0) + num*groupNum);
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> countMap = stack.peek();
                countMap.put(atom, countMap.getOrDefault(atom, 0) + num);
            }
        }
        //原子数量输出
        Map<String, Integer> map = stack.peek();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            int num = entry.getValue();
            sb.append(atom);
            if (num > 1) {
                sb.append(num);
            }
        }
        
        return sb.toString();
    }
    
    public String parseAtom() {
        StringBuffer sb = new StringBuffer();
        sb.append(formula.charAt(i++));
        if (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }
    
    /**
     * 计算原子组的数量
     * @return
     */
    public int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1;
        }
        //数字可能大于10
        int num = 0;
        while(i < n && Character.isDigit(formula.charAt(i))) {
            num = num*10 + formula.charAt(i++) - '0';
        }
        return num;
    }
}
