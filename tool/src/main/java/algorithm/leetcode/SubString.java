package algorithm.leetcode;

public class SubString {
    static int countSubString(String s) {
        int length = s.length();
        int count = 0;
        // 根据回文中心取值
        for (int i = 0; i < 2 * length - 1; i++) {
            int li = i / 2, ri = li + i % 2;
            while (li >= 0 && ri < length && s.charAt(li) == s.charAt(ri)) {
                count++;
                li--;
                ri++;
            }
        }
        return count;
    }

    // 字符串是否回文
    static boolean pd(String s) {
        boolean result = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
