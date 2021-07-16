package 华为OD机试;

/**
 * @author wangyao
 * @date 2021-7-5 17:41
 * @description:
 * 1,0,1,1,0,1,1,1表示停车位的情况，1表示有车，0表示空位，有三种车，分别占1,2,3个宽度的车位，求最少有几辆车
 */
public class HowManyCar {
    
    public static void main(String[] args) {
        String str = "0,1,0,1,1,0,1,1,1,1,0";
        String target = str.replace(",","");
        String[] car = target.split("0");
        int count = 0;
        for (String a : car) {
            if (!"".equals(a)) {
                count += a.length()%3;
            }
        }
        System.out.println(count);
    }
}
