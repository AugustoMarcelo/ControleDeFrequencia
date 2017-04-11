package br.com.filarmonica.models;

import java.util.Date;
import java.util.Objects;

/**
 * CLASSE MODELO PARA REPRESENTAR O PAGAMENTO DE FALTAS
 *
 * @author Marcelo Augusto
 */
public class Pagamento {

    private int id;
    private double valor;
    private Musico musico;
    private Date data;

    public Pagamento() {
        
    }

    public Pagamento(double valor, Musico musico, Date data) {
        this.valor = valor;
        this.musico = musico;
        this.data = data;
    }

    public Pagamento(int id, double valor, Musico musico, Date data) {
        this.id = id;
        this.valor = valor;
        this.musico = musico;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Musico getMusico() {
        return musico;
    }

    public void setMusico(Musico musico) {
        this.musico = musico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.musico);
        hash = 37 * hash + Objects.hashCode(this.data);
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
        final Pagamento other = (Pagamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.musico, other.musico)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pagamento{" + "valor=" + valor + ", musico=" + musico + ", data=" + data + '}';
    }

}
