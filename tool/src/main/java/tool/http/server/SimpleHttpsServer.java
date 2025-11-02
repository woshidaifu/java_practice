package tool.http.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.*;
import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.concurrent.Executors;

public class SimpleHttpsServer {
    private final HttpsServer server;
    private final ObjectMapper objectMapper;

    public SimpleHttpsServer(int port, String keystorePath, String password) throws Exception {
        // 1. 创建 HTTPS server
        server = HttpsServer.create(new InetSocketAddress(port), 0);

        // 2. 配置 SSL
        char[] pass = password.toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");
        try (FileInputStream fis = new FileInputStream(keystorePath)) {
            ks.load(fis, pass);
        }

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, pass);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, null);

        server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                try {
                    SSLContext context = getSSLContext();
                    SSLEngine engine = context.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());
                    params.setSSLParameters(context.getDefaultSSLParameters());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        server.setExecutor(Executors.newFixedThreadPool(10));
        objectMapper = new ObjectMapper();
    }

    public void addJsonHandler(String path, Object responseData) {
        server.createContext(path, new JsonHandler(responseData));
    }

    public void start() {
        server.start();
        System.out.println("✅ HTTPS 服务器已启动，监听端口: " + server.getAddress().getPort());
    }

    public void stop() {
        server.stop(0);
        System.out.println("服务器已停止");
    }

    private class JsonHandler implements HttpHandler {
        private final Object responseData;

        public JsonHandler(Object responseData) {
            this.responseData = responseData;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                String jsonResponse = objectMapper.writeValueAsString(responseData);
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(jsonResponse.getBytes());
                }
            } catch (Exception e) {
                String errorMessage = "{\"error\":\"" + e.getMessage() + "\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(500, errorMessage.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorMessage.getBytes());
                }
            } finally {
                exchange.close();
            }
        }
    }
}
