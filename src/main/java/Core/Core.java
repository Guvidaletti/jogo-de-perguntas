package Core;

import jogo.Jogador;
import jogo.Jogo;
import socket.Client;
import socket.Server;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Core {
  private static long idAtual = 1;
  private static Map<Long, Jogo> jogos = new HashMap<>();
  private static Server server;

  static {
    server = new Server();
  }

  public static void comandoRecebido(String mensagem, int port, InetAddress address) {
    String[] comando = mensagem.split(";");
    switch (comando[0]) {
      case "creategame" -> {
        long _id = idAtual;
        Jogo novoJogo = new Jogo(_id);
        String username = comando[1];
        novoJogo.adicionarJogador(username, port, address);
        jogos.put(_id, novoJogo);
        Client.sendToFront(("created;" + _id + ";" + username), address, port);
        idAtual++;
      }
      case "join" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        String username = comando[2];
        if (jogo != null) {
          jogo.adicionarJogador(username, port, address);
          Client.sendToFront("joined;" + jogo.getId(), address, port);
        } else {
          System.err.println("Jogo: " + comando[1] + " não encontrado!");
        }
      }
      case "start" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        if (jogo != null) {
          jogo.start();
        } else {
          System.err.println("Jogo não encontrado!");
        }
      }
      case "acertou" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        if (jogo != null) {
          jogo.count++;
          Jogador jogador = jogo.getJogadores().get(comando[2]);
          jogador.addPosicao();
//          if (jogador.getPosicao() <= 9) { CODIGO MODIFICADO PARA APRESENTACAO
          if (jogador.getPosicao() <= 3) {
            jogo.sendMessageToAllPlayers("hit;" + comando[2]);
            if (jogo.count == jogo.getJogadores().size()) {
              jogo.count = 0;
              jogo.semaphore.release();
            }
          } else {
            jogo.sendMessageToAllPlayers("win;" + comando[2]);
            jogo.ganhador = comando[2];
            jogo.semaphore.release();
          }
        } else {
          System.err.println("Jogo não encontrado!");
        }
      }
      case "errou" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        if (jogo != null) {
          jogo.count++;
          jogo.sendMessageToAllPlayers("miss;" + comando[2]);
          if (jogo.count == jogo.getJogadores().size()) {
            jogo.count = 0;
            jogo.semaphore.release();
          }
        } else {
          System.err.println("Jogo não encontrado!");
        }
      }
      case "status" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        if (jogo != null) {
          jogo.sendMessageToAllPlayers("status;" + jogo.getStatus());
        } else {
          System.err.println("Jogo não encontrado!");
        }
      }
      case "quit" -> {
        Jogo jogo = jogos.get(Long.valueOf(comando[1]));
        if (jogo != null) {
          jogo.getJogadores().remove(comando[2]);
          jogo.sendMessageToAllPlayers("quited;" + comando[2]);
        } else {
          System.err.println("Jogo não encontrado!");
        }
      }
      default -> {
        System.out.println("Comando inválido: " + mensagem);
      }
    }
  }

  public static void main(String[] args) {
    try {
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
