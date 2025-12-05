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
import java.net.URLDecoder;

public class EchoGetHandler extends CustomHttpHandler {
  @Override
  public void handle(HttpExchange he) throws IOException {
    increment();
    printPageCounter("EchoGetHandler");
	Map<String, Object> parameters = new HashMap<String, Object>();
	URI requestedUri = he.getRequestURI();
	String query = requestedUri.getRawQuery();
	String response = "";
//	response = "<h1> Query: "+query+"</h1>\n";
	parseQuery(query, parameters);
	for (String key : parameters.keySet()) {
		response += key + " = " + parameters.get(key) + "\n";
	}
	he.sendResponseHeaders(200, response.length());
	OutputStream os = he.getResponseBody();
	os.write(response.getBytes());
	os.close();
  }
  void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

       if (query != null) {
                 String pairs[] = query.split("[&]");
                 for (String pair : pairs) {
                          String param[] = pair.split("[=]");
                          String key = null;
                          String value = null;
                          if (param.length > 0) {
                          key = URLDecoder.decode(param[0],
                                System.getProperty("file.encoding"));
                          }

                          if (param.length > 1) {
                                   value = URLDecoder.decode(param[1],
                                   System.getProperty("file.encoding"));
                          }

                          if (parameters.containsKey(key)) {
                                   Object obj = parameters.get(key);
                                   if (obj instanceof List<?>) {
                                            List<String> values = (List<String>) obj;
                                            values.add(value);

                                   } else if (obj instanceof String) {
                                            List<String> values = new ArrayList<String>();
                                            values.add((String) obj);
                                            values.add(value);
                                            parameters.put(key, values);
                                   }
                          } else {
                                   parameters.put(key, value);
                          }
                 }
           }
    }

}
