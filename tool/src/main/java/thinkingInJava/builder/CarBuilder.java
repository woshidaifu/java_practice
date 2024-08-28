package thinkingInJava.builder;

/**
 * 建造者模式，抽象的builder类
 * @author lipf3
 *
 */
public  interface CarBuilder {
	public abstract void setCarProduct(String carName, String carBrand);
	public abstract CarProduct getCarProduct();
}
