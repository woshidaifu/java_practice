package algorithm.poj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q1009 {
    public static void main(String[] args) {
        // 数据属性
        int length = 0;
        ArrayList<Integer> mapNum = new ArrayList<>();
        // 输入
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] input = str.split(" ");
            // 输入处理
            if (input.length == 1) {
                // 处理长度、结束
                if (Integer.parseInt(input[0]) == 0) {
                    break;
                } else {
                    length = Integer.parseInt(input[0]);
                }
            } else {
                // 录入图像数据
                if (!(Integer.parseInt(input[0]) == 0 && Integer.parseInt(input[1]) == 0)) {
                    for (int i = 0; i < Integer.parseInt(input[1]); i++) {
                        mapNum.add(Integer.parseInt(input[0]));
                    }
                } else {
                    // 录入结束处理
                    handle(mapNum, length);
                    mapNum = new ArrayList<>();
                }
            }
        }
        sc.close();
    }

    // 图像处理
    private static void handle(ArrayList<Integer> mapNum, int length) {
        // 计高高度，然后取余 或 取商判断每个节点的上下左右的index
        int height = mapNum.size() / length;
        int left, right, up, down, leftup,leftdown,rightup,rightdown,target;
        int[] result = new int[mapNum.size()];
        int[] lines = new int[8];
        for (int i = 0; i < mapNum.size(); i++) {
            left = (i % length == 0) ? i : i - 1;
            right = (i % length == length - 1) ? i : i + 1;
            up = (i / length == 0) ? i : i - length;
            down = (i / length == height - 1) ? i : i + length;
            leftup = (up == i || left == i )?i:up-1;
            leftdown = (left == i || down == i)?i:down-1;
            rightup = (up == i || right == i)?i:up+1;
            rightdown = (right == i || down == i)?i:down+1;
            // 判断四个数字的绝对值
            lines[0] = (Math.abs(mapNum.get(i) - mapNum.get(left)));
            lines[1] = (Math.abs(mapNum.get(i) - mapNum.get(right)));
            lines[2] = (Math.abs(mapNum.get(i) - mapNum.get(up)));
            lines[3] = (Math.abs(mapNum.get(i) - mapNum.get(down)));
            lines[4] = (Math.abs(mapNum.get(i) - mapNum.get(leftup)));
            lines[5] = (Math.abs(mapNum.get(i) - mapNum.get(leftdown)));
            lines[6] = (Math.abs(mapNum.get(i) - mapNum.get(rightup)));
            lines[7] = (Math.abs(mapNum.get(i) - mapNum.get(rightdown)));
            Arrays.sort(lines);
            target = lines[7];
            result[i] = target;
        }

        // 输出
        System.out.println(length);
        int count = 1;
        for(int i = 1;i<result.length;i++){
            if ((result[i-1]==result[i]) && i<result.length){
                count++;
                if (i==result.length-1){
                    System.out.print(result[i-1]);
                    System.out.print(" ");
                    System.out.println(count);
                }
            }else{
                System.out.print(result[i-1]);
                System.out.print(" ");
                System.out.println(count);
                count = 1;
            }
        }

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < length; j++) {
//                if ((i*length)+j < mapNum.size()) {
//                    System.out.print(result[(i * length) + j]);
////                    System.out.print(mapNum.get((i*length)+j));
//                    System.out.print(" ");
//                } else {
//                    break;
//                }
//            }
//            System.out.println();
//        }
    }

}
