package tool.webService.webServiceServer;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface TheService {
    //方法注解
    @WebResult(name="addResult")
    public  int add(@WebParam(name="a")int a , @WebParam(name="b")int b);

}
