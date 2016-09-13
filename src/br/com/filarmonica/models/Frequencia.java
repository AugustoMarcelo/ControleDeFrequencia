package br.com.filarmonica.models;

import java.util.Objects;

public class Frequencia {

    private Musico musico;
    private Tocata tocata;
    private boolean presenca;

    public Frequencia() {
    
    }

    public Frequencia(Musico musico, Tocata tocata, boolean presenca) {
        this.musico = musico;
        this.tocata = tocata;
        this.presenca = presenca;
    }

    public Musico getMusico() {
        return musico;
    }

    public void setMusico(Musico musico) {
        this.musico = musico;
    }

    public Tocata getTocata() {
        return tocata;
    }

    public void setTocata(Tocata tocata) {
        this.tocata = tocata;
    }

    public boolean isPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.musico);
        hash = 41 * hash + Objects.hashCode(this.tocata);
        hash = 41 * hash + (this.presenca ? 1 : 0);
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
        final Frequencia other = (Frequencia) obj;
//        if (this.presenca != other.presenca) {
//            return false;
//        }
        if (!Objects.equals(this.musico, other.musico)) {
            return false;
        }
        if (!Objects.equals(this.tocata, other.tocata)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Frequencia{" + "musico=" + musico + ", tocata=" + tocata + ", presenca=" + (presenca?"•":"F") + '}';
    }

    
}
