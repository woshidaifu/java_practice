package algorithm.poj;

import java.util.Scanner;

public class Q1005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt() * 2;
        float[] a = new float[N];
        int m = 0;
        while(m<N){
            a[m] = sc.nextFloat();
            m++;
        }
        float[] squar = new float[N];
        int j = 0;
        for (int i = 0;i<N-1;i=i+2){
            squar[j] = (a[i] * a[i] + a[i+1]*a[i+1])* (float) Math.PI/2;
            j++;
        }
        for (int k = 0; k<N/2;k++){
            int tmp = (int)Math.ceil(squar[k]/50);
            int tmp2 = k+1;
            System.out.println("Property "+tmp2+": This property will begin eroding in year "+tmp+".");
        }
        System.out.println("END OF OUTPUT.");
        sc.close();
    }
}
