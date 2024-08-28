package algorithm.soj;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem1006 {
    public static void main(String[] args) {
        Scanner readIn = new Scanner(System.in);
        while (readIn.hasNext()){

            BigInteger result = BigInteger.valueOf(1);
            if (readIn.hasNextInt()){
                BigInteger a = readIn.nextBigInteger();
                BigInteger i = BigInteger.valueOf(1);
                while(true){
                    result = result.multiply(i);
                    i = i.add(BigInteger.valueOf(1));
                    if (i.compareTo(a)==1){
                        break;
                    }
                }
            }else if(readIn.next().equals("exit")){
                break;
            }
            System.out.println(result);
            readIn.close();
        }
    }
}
