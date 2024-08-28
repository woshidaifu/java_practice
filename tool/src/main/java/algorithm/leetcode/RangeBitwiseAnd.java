package algorithm.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RangeBitwiseAnd {

    private static final Logger logger = LoggerFactory.getLogger(RangeBitwiseAnd.class);

    public static int rangeBitwiseAnd(int m, int n) {
        logger.info("start running");
        char[] result = new char[256];
        char[] binaryTmp = new char[256];
        char[] tmp = java.lang.Integer.toBinaryString(m).toCharArray();

        result = getCharArray(result, tmp);
        for (int i = m + 1; i <= n; i++) {
            tmp = java.lang.Integer.toBinaryString(i).toCharArray();
            binaryTmp = getCharArray(binaryTmp, tmp);
            for (int j = 255; j >= 0; j--) {
                if (binaryTmp[j] == '1' && result[j] == '1') {
                    result[j] = '1';
                } else {
                    result[j] = '0';
                }
            }
        }
        int value = 0;
        for (int i = 0; i < 256; i++) {
            if (result[i] == '1') {
                value = value + (int) Math.pow(2, (255 - i));
            }
        }
        logger.info("end running");
        return value;
    }

    // 补全调整数组
    private static char[] getCharArray(char[] result, char[] tmp) {
        for (int k = 0; k < 256; k++) {
            if (k < tmp.length) {
                result[255 - k] = tmp[tmp.length - k - 1];
            } else {
                result[255 - k] = '0';
            }
        }
        return result;
    }
}
