import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class EchoPutHandler extends CustomHttpHandler {
  @Override
  public void handle(HttpExchange he) throws IOException {
    increment();
    printPageCounter("EchoPutHandler");
	String response = "echoPut: Not Implemented\n";
	he.sendResponseHeaders(200, response.length());
	OutputStream os = he.getResponseBody();
	os.write(response.getBytes());
	os.close();
  }
}
