package jogo;

import org.codehaus.jackson.map.ObjectMapper;
import socket.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Jogo extends Thread {
  private Map<String, Jogador> jogadores;
  private long id;
  public int count = 0;
  public Semaphore semaphore = new Semaphore(0);
  public String ganhador;

  public Jogo(long id) {
    this.id = id;
    jogadores = new HashMap<>();
  }

  public void sendMessageToAllPlayers(String message) {
    jogadores.values().forEach(j -> {
      Client.sendToFront(message, j.getAddress(), j.getPort());
    });
  }

  public void adicionarJogador(String username, int port, InetAddress address) {
    sendMessageToAllPlayers("userEntered;" + username);
    jogadores.put(username, new Jogador(username, address, port));
  }

  public Map<String, Jogador> getJogadores() {
    return jogadores;
  }

  @Override
  public long getId() {
    return id;
  }

  public void sendQuestionToPlayers() {
    count = 0;
    ObjectMapper objectMapper = new ObjectMapper();
    jogadores.values().forEach((jogador) -> {
      Pergunta.Dificuldade dificuldade = null;
      int posicao = jogador.getPosicao();
      if (posicao < 3) {
        dificuldade = Pergunta.Dificuldade.FACIL;
      } else if (posicao < 6) {
        dificuldade = Pergunta.Dificuldade.MEDIO;
      } else {
        dificuldade = Pergunta.Dificuldade.DIFICIL;
      }
      try {
        Client.sendToFront("question;" + objectMapper.writeValueAsString(Perguntas.getPerguntaAleatoria(dificuldade)), jogador.getAddress(), jogador.getPort());
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void run() {
    try {
      sendMessageToAllPlayers("started;" + getId());
      System.out.println("Jogo " + id + " iniciado!");
      Thread.sleep(1200);
      while (ganhador == null) {
        this.sendQuestionToPlayers();
        semaphore.acquire();
      }
      System.out.println(ganhador + "Ganhou o jogo!");

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public String getStatus() {
    StringBuilder sb = new StringBuilder("");
    jogadores.values().forEach(j -> sb.append(j.getUsername() + " está na " + j.getPosicao() + "a casa\n"));
    return sb.toString();
  }
}
