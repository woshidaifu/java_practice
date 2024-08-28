package thinkingInJava.serveletTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest requests,HttpServletResponse response) throws ServletException,IOException {
        String username = requests.getParameter("username");
        // String pwd = requests.getParameter("pwd");
        response.setContentType("text/html,charset=GB18030");
        //返回html页面  
        response.getWriter().println("<html>");  
        response.getWriter().println("<head>");     
        response.getWriter().println("<title>登录信息</title>");      
        response.getWriter().println("</head>");    
        response.getWriter().println("<body>");     
        response.getWriter().println("欢迎【" + username + "】用户登录成功！！！");    
        response.getWriter().println("</body>");    
        response.getWriter().println("</html>");  
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);                     
    }       
    
}