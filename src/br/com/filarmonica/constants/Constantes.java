/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filarmonica.constants;

/**
 *
 * @author Marcelo Augusto
 */
public enum Constantes {
    
    Version("Versão", "1.0.0"),
    DevelopedBy("Desenvolvido por", "Marcelo Augusto");
    
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
