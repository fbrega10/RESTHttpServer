import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class RESTHttpServer {

    static public int port = 3000;
    public static int pageCounter = 0;
    public static HashMap<Class<?>, Integer> counterMap = new HashMap<>();

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
        server.createContext("/statistics", new StatisticsHandler());
        server.setExecutor(null);
        server.start();
    }


    public static void refreshCounterMap(Class<?> cls, Integer counter) {
        counterMap.put(cls, counter);
    }
}

