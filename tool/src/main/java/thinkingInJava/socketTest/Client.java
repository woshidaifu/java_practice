package thinkingInJava.socketTest;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		Socket s = null;
		
		try {
			//客户端读取xml，发送客户端
			s = new Socket("127.0.0.1", 8799);
			OutputStream os = s.getOutputStream();
			SAXReader reader = new SAXReader(); 
			org.dom4j.Document doc = reader.read(new File("test.xml"));	
			XMLWriter writer = new XMLWriter();  
			writer.setOutputStream(os);  
			writer.write(doc); 
			writer.flush();
			System.out.println("before ");
			s.shutdownOutput();
			
			
			//客户端接收
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String info = null;
			System.out.println(br.readLine());
			while ((info = br.readLine()) != null) {
				System.out.println("I am client, Server say :" + info);
				
			}
			System.out.println("after");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(s!=null){
				try{
					s.close();
				}catch(IOException e){
                    e.printStackTrace(); 
				}
			}
		
		}
	}
}
