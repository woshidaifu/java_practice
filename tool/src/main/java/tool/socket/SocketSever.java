package tool.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 生成一个 socketSever，收到 bye 表示结束。
 */
public class SocketSever {

    protected void startServer(int port) {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("socket server listening on :"+ port);
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("client is connected : " + clientSocket.getInetAddress().getHostAddress());
                new Thread(()-> {
                    try(
                        BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                            ){
                        String clientMessage;
                        while((clientMessage = in.readLine())!=null) {
                            if ("bye".equals(clientMessage)){
                                out.write("client says bye,so bye bye");
                                out.newLine();
                                out.flush();
                                break;
                            }else {
                                System.out.println("server receives : " + clientMessage);
                                out.write("<msg>yes i get u</msg>");
                                out.newLine();
                                out.flush();
                            }
                        }
                    }catch(IOException e){
                        System.err.println("wrong with handling socket client : " + e.getMessage());
                    }finally {
                        try{
                            clientSocket.close();
                        }catch(IOException e){
                            System.err.println("error while closing socket client "+e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
