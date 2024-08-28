package thinkingInJava.socketTest;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerThreadWithReturn implements Callable<Object> {
	// 和本线程相关的Socket
	Socket socket = null;

	public ServerThreadWithReturn(Socket socket) {
		this.socket = socket;
	}

	// 需要实现Callable的Call方法
	public String call() throws Exception {
		{
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			OutputStream os = null;
			PrintWriter pw = null;
			try {
				// 获取输入流，并读取客户端信息
				is = socket.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String info = null;
				while ((info = br.readLine()) != null) {// 循环读取客户端的信息
					System.out.println("我是服务器，客户端说：" + info);
				}
				socket.shutdownInput();// 关闭输入流
				// 获取输出流，响应客户端的请求
				os = socket.getOutputStream();
				pw = new PrintWriter(os);

				// 读取解析xml
//				String xmlFile = "test.xml";
//				Dom4jTest.readXML(xmlFile);

				pw.write("当前线程" + Thread.currentThread().getName());
				pw.flush();// 调用flush()方法将缓冲输出
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 关闭资源
				try {
					if (pw != null)
						pw.close();
					if (os != null)
						os.close();
					if (br != null)
						br.close();
					if (isr != null)
						isr.close();
					if (is != null)
						is.close();
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String rStr =  ":hello";
		return rStr;
	}
}
