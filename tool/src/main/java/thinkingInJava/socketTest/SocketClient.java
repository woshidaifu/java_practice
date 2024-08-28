package thinkingInJava.socketTest;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 故意坑人的客户端程序^_^
 * 
 * @author wangkw
 *
 */
public class SocketClient {
	public static ExecutorService es = Executors.newFixedThreadPool(10);
	public static final CountDownLatch doneSignal = new CountDownLatch(10);

	/**
	 * 并发框架主程序，可以不看，主要看下面Sender的内容
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args)
			throws FileNotFoundException, InterruptedException, UnsupportedEncodingException {
		BufferedReader[] brs = new BufferedReader[10];

		// 10并发
		for (int i = 0; i < 10; i++) {
			brs[i] = new BufferedReader(new InputStreamReader(
					new FileInputStream("D:\\temp\\sendfile\\TRANSMSG_ONLE_result." + i + ".txt"), "GBK"));
		}
		long stTime = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			Sender sd = new Sender(brs[i], i, "127.0.0.1", 5678);
			es.execute(sd);
		}
		doneSignal.await();
		// 记录时间仅供参考
		System.out.println("cost total = " + (System.currentTimeMillis() - stTime));
		// 由于没有读outputstream，需要等待，防止未被服务端未读取就退出关闭了连接
		Thread.sleep(10000);
		es.shutdown();
	}

	/**bbb
	 * 具体发送程序
	 * 
	 * @author wangkw
	 *
	 */
	public static class Sender implements Runnable {

		int num;
		BufferedReader br;
		String ip;
		int port;

		Sender(BufferedReader br, int num, String ip, int port) {
			this.br = br;
			this.num = num;
			this.ip = ip;
			this.port = port;
		}

		@Override
		public void run() {
			try {
				String line = br.readLine();
				Random ran = new Random();
				while (line != null) {
					Socket client = new Socket(ip, port);
					String sendbuf = process(line);
					int sendbuflen = sendbuf.getBytes("GBK").length;
					int length = ("00000000" + sendbuflen).length();
					// 请注意!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					// 坑人开始了，好好的一个报文在应用层被两次write
					// 真实生成环境请千万不要这样写，一定要一次write
					// 两次write，由于socket底层缓存发送机制以及网络状况会
					// 有小概率情况会导致2个报文乱序被服务端读取,大概万分之一
					// 为体现公平,这里我手工设置一定概率调整顺序
					// 请考虑单个包收到，黏包收到，逆序收到，祝你们好运!!
					String sendbuflength = ("00000000" + sendbuflen).substring(length - 8, length);
					byte[] sendbufByte = sendbuf.getBytes("GBK");
					byte[] data = new byte[4196 * 2];
					System.arraycopy(sendbuflength.getBytes("GBK"), 0, data, 0, 8);
					System.arraycopy(sendbufByte, 0, data, 8, sendbufByte.length);
					int counter = 0;

					if (ran.nextInt() % 10 != 1) {// 正常报文
						client.getOutputStream().write(data, 0, 4196);
						counter += 4196;
						client.getOutputStream().write(data, counter, sendbufByte.length + 8 - counter);
					} else {// 逆序报文
						client.getOutputStream().write(data, 4196, sendbufByte.length + 8 - 4196);
						client.getOutputStream().write(data, 0, 4196);
					}
					line = br.readLine();
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Thread[" + num + "] finished");
			doneSignal.countDown();
		}

		/**
		 * 解析文件并拼装报文
		 * 
		 * @param readLine
		 * @return
		 */
		private String process(String readLine) {
			String[] area = readLine.split("\\|\\^\\|");
			String sendContent = "<?xml version=\"1.0\" encoding=\"GBK\"?><message>";
			sendContent += "<acctno>" + area[0] + "</acctno>";
			sendContent += "<oppacct>" + area[1] + "</oppacct>";
			sendContent += "<acctname>" + area[2] + "</acctname>";
			sendContent += "<transamt>" + area[3] + "</transamt>";
			sendContent += "<description>" + area[4] + "</description>";
			sendContent += "<confirmno>" + area[5] + "</confirmno>";
			sendContent += "</message>";

			return sendContent;
		}
	}
}
