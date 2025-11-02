package tool.http.client;

import java.io.IOException;

/**
 * Java 8 HTTP客户端测试程序
 * 用于测试连接到本地服务器
 */
public class Java8HttpClientTest {

    public static void main(String[] args) {
        // 本地服务器地址
        String serverUrl = "http://127.0.0.1:12345/api/data";
        
        try {
            System.out.println("开始测试Java 8 HTTP客户端...");
            
            // 测试1: 发送简单POST请求
            System.out.println("\n测试1: 发送简单POST请求");
            String response1 = Java8HttpClient.sendPost(serverUrl, null, "测试数据");
            System.out.println("响应: " + response1);
            
            // 测试2: 发送JSON POST请求
            System.out.println("\n测试2: 发送JSON POST请求");
            String jsonBody = "{\"name\":\"张三\",\"age\":30,\"message\":\"测试JSON请求\"}";
            String response2 = Java8HttpClient.sendJsonPost(serverUrl, jsonBody);
            System.out.println("响应: " + response2);
            
            System.out.println("\n测试完成!");
            
        } catch (IOException e) {
            System.err.println("测试过程中出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}