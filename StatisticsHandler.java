import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String output = getStatistics();
        exchange.sendResponseHeaders(200, output.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(output.getBytes());
        outputStream.close();
    }

    public String getStatistics() {
        Set<Class<?>> set = RESTHttpServer.counterMap.keySet();
        return set.stream()
                .map(cls -> {
                    Integer counter = RESTHttpServer.counterMap.get(cls);
                    return "<h1> Class : " + cls + ", counter : " + counter + "</h1>";
                })
                .collect(Collectors.joining("\n"));
    }
}
