package algorithm.poj;

import java.util.*;

public class Q1007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stringLength = sc.nextInt();
        int stringNumbers = sc.nextInt();
        List<String> dnas = new ArrayList<String>();
        stringLength = stringLength + 1;
        int i = 0;
        while (i < stringNumbers) {
            dnas.add(sc.next());
            i++;
        }
        sortDna(dnas);
        sc.close();
    }

    /**
     * 排序
     * 
     * @param dnas
     */
    private static void sortDna(List<String> dnas) {
        // int i = 0;
        Comparator<Object> comparator = new ComparatorListSort();
        // 排序
        Collections.sort(dnas, comparator);
        Iterator<String> it = dnas.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class ComparatorListSort implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        return getInversionNum((String) o1) - getInversionNum((String) o2);
    }

    /**
     * 获取每个string的逆序数 sb1个小时
     */
    private static int getInversionNum(String dna) {
        int count = 0;
        int length = dna.length();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (dna.charAt(i) > dna.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }
}