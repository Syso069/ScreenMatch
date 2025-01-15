package br.com.alura.screenmatch.excecao;

public class ConversaoDeAnoExeception extends RuntimeException {
    private String mensagem;

    public ConversaoDeAnoExeception(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
