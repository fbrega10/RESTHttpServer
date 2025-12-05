import com.sun.net.httpserver.HttpHandler;

public abstract class CustomHttpHandler implements HttpHandler {
    private Integer pageCounter;

    public CustomHttpHandler() {
        pageCounter = new Integer(0);
    }

    public void increment() {
        ++RESTHttpServer.pageCounter;
        ++pageCounter;
    }

    public void printPageCounter(String pageName) {
        System.out.println("Handler : " + pageName + " counter: " + pageCounter);
    }

    public Integer getPageCounter() {
        return this.pageCounter;
    }
}
