import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class TestClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 6000);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        Random random = new Random();

        String[] protocols = {"TCP", "UDP"};

        while (true) {

            String sourceIP = "192.168.1." + random.nextInt(255);
            String destIP = "8.8.8." + random.nextInt(255);
            int port = 1000 + random.nextInt(9000);
            String protocol = protocols[random.nextInt(protocols.length)];
            int size = 100 + random.nextInt(1500);

            String packet =
                    sourceIP + "," +
                            destIP + "," +
                            port + "," +
                            protocol + "," +
                            size;

            writer.println(packet);

            Thread.sleep(1000); // send packet every second
        }
    }
}
