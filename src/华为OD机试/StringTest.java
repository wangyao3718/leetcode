package 华为OD机试;

/**
 * @author wangyao
 * @date 2021-7-5 21:37
 * @description:
 * 给定两个非常大的正整数A和B，位数在50至100之间。求C＝A+B；
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "1122387781";
        String b = "88762223";
        /*
            1.位数少的字符串前补0
            2.嵌套循环遍历a和b,每位相加，如果有进位，保留到下轮计算
         */
        //1.位数少的字符串前补0
        int na = a.length();
        int nb = b.length();
        if (na > nb) {
            int cha = na - nb;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cha; i++) {
                sb.append("0");
            }
            b = sb.toString() + b;
        } else {
            int cha = nb - na;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < cha; i++) {
                sb.append("0");
            }
            a = sb.toString() + a;
        }
        
        //2.嵌套循环a和b，每位相加，如果有进位，保留到下轮计算
        boolean jw = false;
        StringBuffer sb = new StringBuffer();
        for (int i = a.length() - 1; i >= 0; i--) {
            int num = Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i)));
            if (jw) {
                num = num +1;
                jw = false;
            }
            if (num >= 10) {
                jw = true;
                num = num -10;
                sb.insert(0, num);
            } else {
               sb.insert(0, num);
            }
        }
        System.out.println(sb.toString());
    }
}
