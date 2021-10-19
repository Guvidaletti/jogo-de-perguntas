package jogo;

public class Pergunta {
  public enum Dificuldade {FACIL, MEDIO, DIFICIL}

  private long id;
  private String titulo;
  private String[] opcoes;
  private int correta;
  private Dificuldade dificuldade = Dificuldade.FACIL;

  public Pergunta(long id, Dificuldade dificuldade, String titulo, int correta, String[] opcoes) {
    this.id = id;
    this.dificuldade = dificuldade;
    this.titulo = titulo;
    this.correta = correta;
    this.opcoes = opcoes;
  }

  public Pergunta(long id, String titulo, String[] opcoes, int correta, Dificuldade dificuldade) {
    this.id = id;
    this.titulo = titulo;
    this.opcoes = opcoes;
    this.correta = correta;
    this.dificuldade = dificuldade;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setOpcoes(String[] opcoes) {
    this.opcoes = opcoes;
  }

  public int getCorreta() {
    return correta;
  }

  public void setCorreta(int correta) {
    this.correta = correta;
  }

  public void setDificuldade(Dificuldade dificuldade) {
    this.dificuldade = dificuldade;
  }

  public boolean isRespostaCerta(String resposta) {
    return opcoes[correta].equalsIgnoreCase(resposta);
  }

  public String getTitulo() {
    return titulo;
  }

  public String[] getOpcoes() {
    return opcoes;
  }

  public long getId() {
    return id;
  }

  public Dificuldade getDificuldade() {
    return dificuldade;
  }
}
