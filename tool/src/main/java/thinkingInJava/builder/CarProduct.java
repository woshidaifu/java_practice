package thinkingInJava.builder;

/**
 * 汽车产品
 * @author lipf3
 *
 */
public class CarProduct {
	
	private String carName;
	private String carBrand;
	
	public void setCarName(String name){
		this.carName = name;
	}
	
	public void setCarBrand(String brand){
		this.carBrand = brand;
	}
	
	public void showCarProduct() {
		System.out.println("the car name is "+ carName +" and the car brand is "+ carBrand);
	}
}
