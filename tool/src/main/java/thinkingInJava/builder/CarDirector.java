package thinkingInJava.builder;

/**
 * 汽车导演类
 * @author lipf3
 *
 */
public class CarDirector {
	
	private CarBuilder goodCarBuilder = new ConcreatCarBuilder();
	
	public CarProduct getBMWProduct(){
		goodCarBuilder.setCarProduct("M760", "BMW");
		return goodCarBuilder.getCarProduct();
	}
	
	public CarProduct getBenzProduct(){
		goodCarBuilder.setCarProduct("CLA", "Benz");
		return goodCarBuilder.getCarProduct();
	}
}
