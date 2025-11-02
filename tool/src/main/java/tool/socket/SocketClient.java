package tool.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public void startClient(String server,int port){
        try(
                Socket socketClient = new Socket(server,port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                Scanner scanner = new Scanner(System.in)
                ){
            System.out.println("connecting to server :" + server + ":" + port);
            System.out.println("ready to send xml");
            while (true){
                if ("bye".equals(scanner.nextLine())){
                    out.write("<msg>exit</msg>");
                    out.newLine();
                    out.flush();
                    break;
                }else{
                    //sending
                    String xml = "<msg>hi</msg>";
                    System.out.println("client is sending xml: " + xml);
                    out.write(xml);
                    out.newLine();
                    out.flush();
                    System.out.println("client has finished sending this time ");
                }
                System.out.println("client is waiting for response");
                String response = in.readLine();
                if (response !=null ){
                    System.out.println("client receives response: " + response);
                }else{
                    System.out.println("sever not responding ");
                    break;
                }
                System.out.println("-------------------------");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("stopped");
        }
    }
}
