package tool.webService.webServiceServer;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(endpointInterface="tool.webService.webServiceServer.TheService")
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class TheServiceImpl implements TheService {
    //对外公布接口的具体实现
    @Override
    public int add(int a, int b) {
        System.out.println("a+b="+(a+b));
        return a+b;
    }

}
