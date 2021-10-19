package jogo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Perguntas {
  public static Pergunta[] getPerguntas() {
    return new Pergunta[]{
        new Pergunta(1, Pergunta.Dificuldade.FACIL, "Qual tipo de rede é uma rede local?", 0, new String[]{"LAN", "MAN", "WAN", "PAN", "YAN"}),
        new Pergunta(2, Pergunta.Dificuldade.FACIL, "Protocolo TCP pertence a qual camada?", 3, new String[]{"Física", "Enlace", "Rede", "Transporte", "Sessão"}),
        new Pergunta(3, Pergunta.Dificuldade.FACIL, "Em que ano foi criada a Arpanet?", 1, new String[]{"1968", "1969", "1970", "1971", "1972"}),
        new Pergunta(4, Pergunta.Dificuldade.FACIL, "Quantas camadas tem o modelo OSI?", 3, new String[]{"4", "5", "6", "7", "8"}),
        new Pergunta(5, Pergunta.Dificuldade.FACIL, "Em que ano foi criado o modelo OSI?", 4, new String[]{"1980", "1981", "1982", "1983", "1984"}),
        new Pergunta(6, Pergunta.Dificuldade.FACIL, "Como se divide a topologia fisica?", 1, new String[]{"Barra, Aliança, Estrela, Malha, Híbrida.", "Barramento, Anel, Estrela, Malha, Híbrida.", "Barra, Anel, Enlace, Malha, Híbrida.", "Barramento, Aliança, Asteroide, Estrela, Híbrida.", "Barramento, Anel, Estrela, Malha, Híbrida."}),
        new Pergunta(7, Pergunta.Dificuldade.MEDIO, "Qual desses protocolos NÃO é um protocolo da camada de Rede", 2, new String[]{"IP", "ICMP", "FTP", "RIP", "NAT"}),
        new Pergunta(8, Pergunta.Dificuldade.MEDIO, "Qual é a função DNS?", 2, new String[]{"Fornecer IP do usuário", "Máscara de sub-rede", "Converter endereços IP em nomes", "IP do default gateway", "Protocolo para transferencia de pacotes"}),
        new Pergunta(9, Pergunta.Dificuldade.MEDIO, "O que é um broadcast?", 2, new String[]{"Transmissão para primeiro host da rede", "Transmissão para o último host da rede", "Transmissão para todos os hosts da rede", "Transmissão para alguns os hosts da rede", "Transmissão para nenhum hosts da rede"}),
        new Pergunta(10, Pergunta.Dificuldade.MEDIO, "Desses equipamentos, qual escolhe o melhor caminho dentro da rede ", 4, new String[]{"Switch", "Hub", "Gateways", "Firewall", "Roteador"}),
        new Pergunta(11, Pergunta.Dificuldade.MEDIO, "Qual desses cabos foi o primeiro a ser criado?", 3, new String[]{"Cabo par trançado", "Cabro de fibra óptica", "Cabo cat5/6", "Cabo coaxial", "Cabo RJ-45"}),
        new Pergunta(12, Pergunta.Dificuldade.MEDIO, "Qual camada do modelo OSI é responsável por corrigir erros que possam acontecer no nível físico? ", 0, new String[]{"Enlace", "Física", "Rede", "Transporte", "aplicação"}),
        new Pergunta(13, Pergunta.Dificuldade.DIFICIL, "Na classe padrão C, quantos bytes são reservados para redes?", 2, new String[]{"1 Byte", "2 Bytes", "3 Bytes", "4 Bytes", "5 Bytes"}),
        new Pergunta(14, Pergunta.Dificuldade.DIFICIL, "Qual é o protocolo de configuração de IP? ", 3, new String[]{"TCP", "HTTP", "DNS", "DHCP", "SMTP"}),
        new Pergunta(15, Pergunta.Dificuldade.DIFICIL, "Qual camada pertence ao protocolo IP?", 2, new String[]{"Física", "Enlace", "Rede", "Transporte", "Sessão"}),
        new Pergunta(16, Pergunta.Dificuldade.DIFICIL, "Na transmissão Half-Duplex?", 2, new String[]{"Transmissão ocorre em um unico sentido", "Transmissão ocorre nos dois sentidos de forma simultânea", "Transmissão ocorre nos dois sentidos de forma alternada", "Transmissão ocorre no sentido que o host desejar", "Transmissão ocorre duas vezes por segundo."}),
        new Pergunta(17, Pergunta.Dificuldade.DIFICIL, "Quais são as principais funções da camada de rede?", 4, new String[]{"Encaminhamento e Empacotamento", "Empacotamento e Roteamento", "Segmentação e Roteamento", "Segmentação e Emcaminhamento", "Encaminhamento e roteamento"})
    };
  }

  public static Pergunta getPerguntaAleatoria(Pergunta.Dificuldade dificuldade) {
    Random random = new Random();
    switch (dificuldade) {
      default:
      case FACIL:
        List<Pergunta> faceis = Arrays.stream(getPerguntas()).filter(pergunta -> pergunta.getDificuldade().equals(Pergunta.Dificuldade.FACIL)).collect(Collectors.toList());
        return faceis.get(random.nextInt(faceis.size()));
      case MEDIO:
        List<Pergunta> medias = Arrays.stream(getPerguntas()).filter(pergunta -> pergunta.getDificuldade().equals(Pergunta.Dificuldade.MEDIO)).collect(Collectors.toList());
        return medias.get(random.nextInt(medias.size()));
      case DIFICIL:
        List<Pergunta> dificeis = Arrays.stream(getPerguntas()).filter(pergunta -> pergunta.getDificuldade().equals(Pergunta.Dificuldade.DIFICIL)).collect(Collectors.toList());
        return dificeis.get(random.nextInt(dificeis.size()));
    }
  }

  public static boolean isCorreta(long id, String resposta) {
    return Arrays.stream(getPerguntas()).anyMatch(p -> p.getId() == id && p.isRespostaCerta(resposta));
  }
}
