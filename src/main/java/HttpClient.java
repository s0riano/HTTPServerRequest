import java.io.IOException;
import java.net.Socket;

public class HttpClient {

    public static void main(String[] args) throws IOException {
        var socket = new Socket("https://httpbin.org/html", 80);

        socket.getOutputStream().write((
                "https://httpbin.org/html"
        ).getBytes());
    }

}
