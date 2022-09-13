import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class HttpClient {

    private Map<String, String> headers = new TreeMap<>();
    private int statusCode;

    public HttpClient(String host, int port, String requestTarget) throws IOException {

        var socket = new Socket(host, 80);

        socket.getOutputStream().write(
                ("GET " + requestTarget + " HTTP/1.1\r\n" +
                        "Connection: close\r\n" +
                        "Host: " + host + "\r\n" +
                        "\r\n"
                ).getBytes()
        );

        String line = readLine(socket);
        this.statusCode = Integer.parseInt(line.split(" ")[1]);

        while (!(line = readLine(socket)).isEmpty()){
            String[] parts = line.split(":\\s*");
            headers.put(parts[0],parts[1]);
        }



    }

    private static String readLine(Socket socket) throws IOException {
        StringBuilder line = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != '\r'){
            line.append((char)c);
        }
        socket.getInputStream().read();
        System.out.println(line);
        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        var socket = new Socket("httpbin.org", 80);

        socket.getOutputStream().write((
                "GET /html HTTP/1.1\r\n" +
                "Connection: close\r\n" +
                "Host: httpbin.org\r\n" +
                 "\r\n"
                ).getBytes()
        );

        int c;
        while ((c = socket.getInputStream().read())  != -1){
            System.out.print((char)c);
        }

    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    public String getResponseBody() {
        return null;
    }
}
