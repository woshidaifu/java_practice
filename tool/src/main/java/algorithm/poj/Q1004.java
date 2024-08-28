package algorithm.poj;

import java.util.Formatter;
import java.util.Scanner;

public class Q1004 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        float total = 0;
        float money = 0;
        while (i < 12) {
            money = sc.nextFloat();
            total += money;
            i++;
        }
        float average = total / 12;
        Formatter formatter = new Formatter();
        String result = formatter.format("%.2f", average).toString();
        System.out.println("$" + result);
        formatter.close();
        sc.close();
    }
}
