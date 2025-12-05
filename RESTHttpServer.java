import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RESTHttpServer {

    static public int port = 3000;
    public static int pageCounter = 0;

    public static void main(String[] args) throws IOException {
        if (args.length > 1 && args[0].equals("-port")) try {
            port = Integer.parseInt(args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RootHandler(port));
        server.createContext("/echoHeader", new EchoHeaderHandler());
        server.createContext("/echoGet", new EchoGetHandler());
        server.createContext("/echoPost", new EchoPostHandler());
        server.createContext("/echoPut", new EchoPutHandler());
        server.createContext("/echoDelete", new EchoDeleteHandler());
        server.setExecutor(null);
        server.start();
    }
}

