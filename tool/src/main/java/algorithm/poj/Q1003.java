package algorithm.poj;

import java.util.Scanner;

public class Q1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextFloat()) {
            float length = sc.nextFloat();
            if (length != 0.00) {
                System.out.println(cardNumber(length) + " card(s)");
            } else {
                break;
            }
        }
        sc.close();
    }
    //求和
    static int cardNumber(float length) {
        float result = 0f;
        int cardNum = 0;
        while (result <= length) {
            result += (float) 1 / (cardNum + 2);
            cardNum++;
        }
        return cardNum;
    }
}
