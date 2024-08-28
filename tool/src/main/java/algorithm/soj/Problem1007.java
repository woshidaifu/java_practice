package algorithm.soj;

import java.util.Scanner;

public class Problem1007 {
    public static void main(String[] args) {
        Scanner readIn = new Scanner(System.in);
        while (readIn.hasNext()){
            String tmp = readIn.next();
            if (!tmp.equals("exit")){
                int begin = tmp.indexOf("<acqInsCode>");
                int end = tmp.indexOf("</acqInsCode>");
                String num = tmp.substring(begin+12 , end);
                boolean result = isHW(num);
                System.out.println(result);
            }else{
                break;
            }
            readIn.close();
        }
    }

    static boolean isHW(String str){
        boolean result = true;
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == sb.charAt(i)) {
                count++;
            }
        }
        if (count == str.length()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}
