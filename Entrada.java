package Mongo;

public class Entrada {
    private String usuario;
    private int Senha;
    private int score;

    // Construtor que aceita todos os campos
    public Entrada(String usuario, int senha, int score) {
        this.usuario = usuario;
        this.Senha = senha;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getSenha() {
        return Senha;
    }

    public void setSenha(int Senha) {
        this.Senha = Senha;
    }
}
