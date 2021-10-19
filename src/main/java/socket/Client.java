package socket;

import java.io.IOException;
import java.net.*;

public class Client {

  public static void sendToFront(String message, InetAddress address, int port) {
    System.out.println("Enviando: " + message + " - " + address + ":" + port);
    try {
      byte[] buf = message.getBytes();
      DatagramPacket packet
          = new DatagramPacket(buf, buf.length, address, port);
      Server.socket.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
