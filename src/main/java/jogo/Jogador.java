package jogo;

import java.net.InetAddress;

public class Jogador {

  private String username = "";
  private InetAddress address;
  private int port;
  private int posicao = 1;

  public Jogador(String username, InetAddress address, int port) {
    this.username = username;
    this.address = address;
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public InetAddress getAddress() {
    return address;
  }

  public int getPort() {
    return port;
  }

  public int getPosicao() {
    return posicao;
  }

  public void addPosicao() {
    this.posicao++;
  }
}
