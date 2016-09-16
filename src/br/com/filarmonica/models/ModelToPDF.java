package br.com.filarmonica.models;

import java.util.Objects;

/* CLASSE UTILIZADA PARA MOSTRAR OS DADOS DO MÚSICO NO PDF*/

public class ModelToPDF {

    private String nomeMusico;
    private String presenca;
    
    public ModelToPDF() {
        
    }
    
    public ModelToPDF(String nomeMusico, String presenca) {
        this.nomeMusico = nomeMusico;
        this.presenca = presenca;
    }

    public String getNomeMusico() {
        return nomeMusico;
    }

    public void setNomeMusico(String nomeMusico) {
        this.nomeMusico = nomeMusico;
    }

    public String getPresenca() {
        return presenca;
    }

    public void setPresenca(String presenca) {
        this.presenca = presenca;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.nomeMusico);
        hash = 37 * hash + Objects.hashCode(this.presenca);
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
        final ModelToPDF other = (ModelToPDF) obj;
        if (!Objects.equals(this.nomeMusico, other.nomeMusico)) {
            return false;
        }
        if (!Objects.equals(this.presenca, other.presenca)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModelToPDF{" + "nomeMusico=" + nomeMusico + ", presenca=" + presenca + '}';
    }
}
