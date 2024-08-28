package designPattern.Factory;

public class FactoryPattern {
    public static Product produce(String productName) throws Exception{
        switch (productName){
            case "car":
                return new Car();
            case "bus":
                return new Bus();
            default:
                throw new Exception("no this product");
        }
    }
}
