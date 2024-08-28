package tool.webService.webServiceClient;

public class Client {
    public static void main(String[] args) {
        TheServiceImplService myService = new TheServiceImplService();
        TheService theService = myService.getTheServiceImplPort();
        System.out.println(theService.add(112,111));
    }
}
