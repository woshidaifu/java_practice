package thinkingInJava.builder;

/**
 * 调用建造者模式
 * @author lipf3
 *
 */
public class Main {
	public static void main(String[] args) {
		CarDirector goodCarDirector = new CarDirector();
		CarProduct bwmCarProduct = goodCarDirector.getBMWProduct();
		bwmCarProduct.showCarProduct();
		CarProduct benzCarProduct = goodCarDirector.getBenzProduct();
		benzCarProduct.showCarProduct();
	}
}
