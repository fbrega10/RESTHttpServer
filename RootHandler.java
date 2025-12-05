import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class RootHandler extends CustomHttpHandler {
  private int port;

  @Override
  public void handle(HttpExchange he) throws IOException {
    increment();
    printPageCounter("RootHandler");
	String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + port + "</h1>";
	he.sendResponseHeaders(200, response.length());
	OutputStream os = he.getResponseBody();
	os.write(response.getBytes());
	os.close();
  }
  
  RootHandler(int p) {
	port = p;
  }
}
