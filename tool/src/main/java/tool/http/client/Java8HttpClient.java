package tool.http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 基于Java 8原生API的HTTP客户端
 * 不依赖第三方库，兼容Java 8
 */
public class Java8HttpClient {

    /**
     * 发送POST请求
     *
     * @param urlStr 请求URL
     * @param headers 请求头
     * @param body 请求体
     * @return 响应内容
     * @throws IOException 如果请求失败
     */
    public static String sendPost(String urlStr, Map<String, String> headers, String body) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // 设置请求方法
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        
        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        
        // 设置请求体
        if (body != null && !body.isEmpty()) {
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }
        
        // 获取响应
        int responseCode = conn.getResponseCode();
        StringBuilder response = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    responseCode >= 400 ? conn.getErrorStream() : conn.getInputStream(), 
                    StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        
        return response.toString();
    }
    
    /**
     * 发送JSON格式的POST请求
     *
     * @param urlStr 请求URL
     * @param jsonBody JSON格式的请求体
     * @return 响应内容
     * @throws IOException 如果请求失败
     */
    public static String sendJsonPost(String urlStr, String jsonBody) throws IOException {
        Map<String, String> headers = new java.util.HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        
        return sendPost(urlStr, headers, jsonBody);
    }
}