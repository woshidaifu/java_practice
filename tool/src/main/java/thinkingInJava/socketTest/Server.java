package thinkingInJava.socketTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// Logger logger1 = Logger.getLogger(Server.class);

		// 10线程，写map
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Map<String, String> targetMap = new ConcurrentHashMap<String, String>();

		try {
			// 服务器端Socket
			ServerSocket serverSocket = new ServerSocket(5678);
			Socket socket = null;
			System.out.println("***服务器启动，等待客户端的连接***");

			// 服务端超时20s
			serverSocket.setSoTimeout(20 * 1000);

			// 循环监听等待客户端的连接
			while (true) {
				try {
					socket = serverSocket.accept();
					pool.execute(new ServerThread(socket, targetMap));
				} catch (SocketTimeoutException s) {
					System.out.println("20s 无连接，默认客服端发送完毕，开始写文件");
					break;
				}
			}
			// 发送结束后，遍历map，写到文件中
			File targetFile = new File("D:\\temp\\sendfile\\result.txt");
			FileOutputStream out = new FileOutputStream(targetFile, true);
			int count = 0;
			System.out.println(targetMap.size());
			for (String data : targetMap.values()) {
				out.write(data.getBytes("utf-8"));
				count++;
			}
            out.close();
			System.out.println("写入记录共" + count + "条");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ServerThread extends Thread {

	static public Logger logger = LoggerFactory.getLogger(ServerThread.class); 	//设定配置文件的位置，如果不设置则要把配置文件放到class目录或根目录
	Socket socket = null;
	Map<String, String> map = null;

	InputStream is = null;

	public ServerThread(Socket socket, Map<String, String> targetMap) {
		this.socket = socket;
		this.map = targetMap;
	}

	// 线程执行的操作，响应客户端的请求
	public void run() {
		try {
			// 读报文头
			is = socket.getInputStream();
			int length = 0;
            byte[] dataBegin = new byte[8];
            String data = null;
            is.read(dataBegin);
			try {
				//正常报文，按长度进行粘包处理
				length = Integer.valueOf(new String(dataBegin, "GBK"));
				byte[] dataTmp = new byte[length];
				is.read(dataTmp,0,length);
				data = new String(dataTmp, "GBK");
                // 报文解析,去重
                process(data,"正常报文",length);
			} catch (NumberFormatException ne) {
                // length不是数字，说明逆序，逆序重组

                //首先获取所有的逆序报文
				byte[] dataTmp = new byte[4196*2];
				is.read(dataTmp,8,4196*2-8);
				System.arraycopy(dataBegin,0,dataTmp,0,8);
				//遍历逆序报文数组，通过<?xml位置，判定长度i
                String targetx = "<?xml";
                byte[] target = new byte[5];
                System.arraycopy(targetx.getBytes("GBK"),0,target,0,5);
                int i = byteIndexOf(dataTmp,target);

                //获取长度
                byte[] dataLength = new byte[8];
                System.arraycopy(dataTmp,i-8,dataLength,0,8);
                String lengthR = new String(dataLength,"GBK");

				try{
                    int realLength = Integer.valueOf(lengthR);
                    int part1 = 4196-8;
                    int part2 = realLength+8-4196;
                    byte[] dataPart1 = new byte[part1];
                    byte[] dataPart2 = new byte[part2];

                    System.arraycopy(dataTmp,0,dataPart2,0,part2);
                    System.arraycopy(dataTmp,part2+8,dataPart1,0,part1);


                    String data1 = new String(dataPart1,"GBK");
                    String data2 = new String(dataPart2,"GBK");

                    String dataFinal = data1+data2;
                    process(dataFinal,"逆序报文",realLength);
                }catch (NumberFormatException e){
                    System.out.println("长度部分有误的"+data);
                }
			} catch (ArrayIndexOutOfBoundsException ae) {
				ae.printStackTrace();
			}
			Thread.sleep(100);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭资源
				if (is != null)
					is.close();
				if (socket != null)
					socket.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 解析xml，存入同步的map中去重
	private void process(String data,String source,int length) {
		
		String confirmNumString = null;
		try {
			confirmNumString = data.substring(data.indexOf("<confirmno>") + 11, data.indexOf("</confirmno>"));
			data = data.replace("<?xml version=\"1.0\" encoding=\"GBK\"?><message>", "");
			data = data.replace("<acctno>", "");
			data = data.replace("</acctno>", "^|^");
			data = data.replace("<oppacct>", "");
			data = data.replace("</oppacct>", "^|^");
			data = data.replace("<acctname>", "");
			data = data.replace("</acctname>", "^|^");
			data = data.replace("<transamt>", "");
			data = data.replace("</transamt>", "^|^");
			data = data.replace("<description>", "");
			data = data.replace("</description>", "^|^");
			data = data.replace("<confirmno>", "");
			data = data.replace("</confirmno>", "^|^");
			data = data.replace("</message>", "");
			map.put(confirmNumString, data);
		} catch (Exception e) {
			System.out.println("来源于  "+source+"检测不到confirmnum，长度是"+length);
			logger.info(data);
			//随机一个数
			map.put(String.valueOf(new Random().nextInt()),data);
		}
	}



    /// 定位指定的 System.Byte[] 在此实例中的第一个匹配项的索引。
    /// </summary>
    /// <param name="srcBytes">源数组</param>
    /// <param name="searchBytes">查找的数组</param>
    /// <returns>返回的索引位置；否则返回值为 -1。</returns>
    public  int byteIndexOf(byte[] b, byte[] bb)
    {
            if (b == null || bb == null || b.length == 0 || bb.length == 0 || b.length<bb.length)
                return -1;

            int i, j;
            for (i = 0; i < b.length - bb.length + 1; i++)
            {
                if (b[i] == bb[0])
                {
                    for (j = 1; j < bb.length; j++)
                    {
                        if (b[i + j] != bb[j])
                            break;
                    }
                    if (j == bb.length)
                        return i;
                }
            }
            return -1;
        }
}
