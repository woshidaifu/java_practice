package tool.webService.webServiceServer;

import javax.xml.ws.*;

public class Server {
    public static void main(String[] args) {
        //web路径
        String address="http://localhost:8888/myservice";
        //配置路径与该路径下的接口实现类
        Endpoint.publish(address, new tool.webService.webServiceServer.TheServiceImpl());
        System.out.println("publish success");
    }
}
