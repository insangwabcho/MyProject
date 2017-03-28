package kwanwoo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiClient {

  public void chatClient(String sname) throws UnknownHostException, IOException {

    try {
      String ServerIP = "localhost";
      Socket socket = new Socket(ServerIP, 9999);
      Thread sender = new Sender(socket, sname);
      Thread receiver = new Receiver(socket);
      sender.start();
      receiver.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
