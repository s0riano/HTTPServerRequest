import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {

    @Test
    public void shouldReadStatusCode() throws IOException {
        var client = new HttpClient("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }

    @Test
    public void shouldReadErrorStatusCode() throws IOException {
        var client = new HttpClient("httpbin.org", 80, "/finnes/ikke");
        assertEquals(404, client.getStatusCode());
    }

    @Test
    void shouldReadHeader() throws IOException {
        var client = new HttpClient("httpbin.org",80,"/html");

        assertEquals("close",client.getHeader("Connection"));
    }


    @Test
    void sisteTest() throws IOException {
        var client = new HttpClient("httpbin.org", 80, "/html");
        //assertTrue(client.getResponseBody().startsWith("<!DOCTYPE html>"));
        //assertTrue(client.getResponseBody().endsWith("  </body>\n</html>"));
    }



}