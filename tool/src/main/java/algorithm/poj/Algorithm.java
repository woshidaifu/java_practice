package algorithm.poj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {
	public static void main(String[] args) {

		ArrayList<Float> inputNum = new ArrayList<Float>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			Float tmp = scanner.nextFloat();
			if (tmp != 0 && tmp != -1) {
				inputNum.add(tmp);
			} else if (tmp == 0) {
				method(inputNum);
				inputNum = new ArrayList<Float>();
				continue;
			} else if (tmp == -1) {
				System.exit(0);
			}
		}
		scanner.close();
	}

	// 逻辑处理函数
	static void method(List<Float> inputNum) {
		int count = 0;
		for (int i = 0; i < inputNum.size(); i++) {
			for (int j = i + 1; j < inputNum.size(); j++) {
				if (inputNum.get(i) / inputNum.get(j) == 2 || inputNum.get(j) / inputNum.get(i) == 2) {
					count++;
				}
			}
		}
		System.out.print(count);
	}
}
