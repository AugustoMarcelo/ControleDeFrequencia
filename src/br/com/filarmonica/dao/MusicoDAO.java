package br.com.filarmonica.dao;

import br.com.filarmonica.models.Musico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MusicoDAO {

    Conexao conexaoBD;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public MusicoDAO() {
        conexaoBD = Conexao.getInstance();
        conn = conexaoBD.getConnection();
    }
    
    public boolean insert(Musico m) {
        String sql = "INSERT INTO musicos (nome, apelido, telefone, email, instrumento, ano_ingresso) VALUES (?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getNome());
            ps.setString(2, m.getApelido());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getEmail());
            ps.setString(5, m.getInstrumento());
            ps.setInt(6, m.getAnoIngresso());
            int insertOk = ps.executeUpdate();
            if(insertOk == 1) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); //RETIRAR
            return false;
        }
        return false;
    }
}
