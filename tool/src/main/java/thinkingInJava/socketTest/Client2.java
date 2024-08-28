package thinkingInJava.socketTest;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8799) ;
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("Hello server, I am client2");
			pw.flush();
			
			s.shutdownOutput();
			
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("I am client, Server say :" + info);
			}
			
			br.close();
			isr.close();
			is.close();
			pw.close();
			os.close();
			s.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
