package tool.http.server;

/**
 * HTTP服务器示例程序
 */
public class RunHttpsServer {
    public static void main(String[] args) throws Exception {
        String keystorePath = SimpleHttpsServer.class
                .getClassLoader()
                .getResource("server.keystore")
                .getPath();

        SimpleHttpsServer server = new SimpleHttpsServer(8081, keystorePath, "changeit");


        server.addJsonHandler("/api/data", new java.util.HashMap<String, Object>() {{
            put("msg", "Hello HTTPS");
            put("status", "ok");
        }});

        server.start();
    }
}
