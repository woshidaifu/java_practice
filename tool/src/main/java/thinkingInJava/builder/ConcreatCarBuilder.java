package thinkingInJava.builder;

/**
 * 具体的建造者
 * @author lipf3
 *
 */
public class ConcreatCarBuilder implements CarBuilder {

	private CarProduct goodCarProduct = new CarProduct();
	
	@Override
	public void setCarProduct(String carName, String carBrand) {
		goodCarProduct.setCarName(carName);
		goodCarProduct.setCarBrand(carBrand);
	}
	
	
	@Override
	public CarProduct getCarProduct() {
		return this.goodCarProduct;
	}


	
}
