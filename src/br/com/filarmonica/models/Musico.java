/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filarmonica.models;

import java.util.Objects;

/**
 *
 * @author Marcelo Augusto
 */
public class Musico {
    
    private int id;
    private String nome;
    private String apelido;
    private String telefone;
    private String instrumento;
    private int anoIngresso;

    public Musico() {
    
    }

    public Musico(String nome, String apelido, String telefone, String instrumento, int anoIngresso) {
        this.nome = nome;
        this.apelido = apelido;
        this.telefone = telefone;
        this.instrumento = instrumento;
        this.anoIngresso = anoIngresso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.apelido);
        hash = 29 * hash + Objects.hashCode(this.telefone);
        hash = 29 * hash + Objects.hashCode(this.instrumento);
        hash = 29 * hash + this.anoIngresso;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Musico other = (Musico) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.anoIngresso != other.anoIngresso) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.apelido, other.apelido)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.instrumento, other.instrumento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Músico \n{ " + "\n\t[nome] => " + nome + ", \n\t[apelido] => " + apelido + ", \n\t[telefone] => " + telefone + ", \n\t[instrumento] => " + instrumento + ", \n\t[anoIngresso] => " + anoIngresso + "\n}";
    } 
}
