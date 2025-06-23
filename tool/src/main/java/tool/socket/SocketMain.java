package tool.socket;

import org.springframework.beans.factory.annotation.Autowired;

public class SocketMain {
    public static void main(String[] args) {
        SocketSever socketSever = new SocketSever();
        SocketClient socketClient = new SocketClient();

        // start server
        new Thread(()->{
            socketSever.startServer(8888);
        }).start();

        // start client
        new Thread(()->{
            socketClient.startClient("localhost",8888);
        }).start();
    }
}