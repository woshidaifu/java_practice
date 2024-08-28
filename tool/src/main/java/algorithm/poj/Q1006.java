package algorithm.poj;

import java.util.Scanner;

public class Q1006 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int e = sc.nextInt();
            int i = sc.nextInt();
            int d = sc.nextInt();
            if (p == -1 && p == e && e == i && i == d) {
                break;
            }
            count = count + 1;
            handle1(count, p, e, i, d);
            handle2(count, p, e, i, d);
            sc.close();
        }
    }

    /**
     * 处理方法1，遍历解方程
     * 
     * @param count
     * @param p
     * @param e
     * @param i
     * @param d
     */
    private static void handle1(int count, int p, int e, int i, int d) {
        int x, y, z;
        int result = 0;
        // 循化计算满足p+23m=e+28j=i+33k的最小xyz值
        LOOP: for (z = 1; z < 23 * 28 + 1; z++) {
            for (y = 1; y < 23 * 33 + 1; y++) {
                for (x = 1; x < 28 * 33 + 1; x++) {
                    if (p + 23 * x == e + 28 * y && e + 28 * y == i + 33 * z) {
                        result = i + 33 * z - d;
                        break LOOP;
                    }
                }
            }
        }
        System.out.println("Case " + count + ": the next triple peak occurs in " + result + " days.");
    }

    /**
     * 处理方法2，余数值处理
     * 
     * @param count
     * @param p
     * @param e
     * @param i
     * @param d
     */
    private static void handle2(int count, int p, int e, int i, int d) {
        // P、E、I均是为了在分别除以23、28、33后余数为1，以方便除p可以余p
        int P = 33 * 28 * 6;
        int E = 23 * 33 * 19;
        int I = 23 * 28 * 2;
        int result = (P * p + E * e + I * i - d) % (23 * 28 * 33);
        if (result <= 0) {
            result += 23 * 28 * 33;
        }
        System.out.println("Case " + count + ": the next triple peak occurs in " + result + " days.");
    }
}
