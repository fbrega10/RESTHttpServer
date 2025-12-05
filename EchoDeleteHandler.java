import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.net.URI;

public class EchoDeleteHandler extends CustomHttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        increment();
        printPageCounter("EchoDeleteHandler");
        String requestMethod = he.getRequestMethod();
        increment();
        String response = "EchoDelete: not implemented\n";
        response += "Requested Method: " + requestMethod + "\n";
        if (requestMethod.equals("DELETE") || requestMethod.equals("delete"))
            he.sendResponseHeaders(200, response.length());
        else
            he.sendResponseHeaders(403, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
