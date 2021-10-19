package socket;

import Core.Core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Thread {
  public static DatagramSocket socket;
  private boolean running;
  private byte[] buf = new byte[999];
  private static int port = 4444;

  static {
    try {
      socket = new DatagramSocket(port);
    } catch (SocketException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    running = true;
    System.out.println("Servidor ON : " + port);
    while (running) {
      try {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength()).trim();
        Core.comandoRecebido(received, packet.getPort(), packet.getAddress());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
