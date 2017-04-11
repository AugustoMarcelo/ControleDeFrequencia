package br.com.filarmonica.constants;

public enum Constantes {
    
    Version("Versão", "1.0.0"),
    DevelopedBy("Desenvolvido por", "Marcelo Augusto"),
    FormatDate("Formatted Date", "dd/MM/yyyy"),
    FormatHour("Formatted Hour", "HH:mm"),
    ConfirmText("Texto de confirmação", "Estou ciente da minha ação");
    
    private String nome;
    private String valor;

    private Constantes(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
}
