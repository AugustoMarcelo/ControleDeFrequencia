package br.com.filarmonica.dao;

import br.com.filarmonica.models.Musico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MusicoDAO {

    Conexao conexaoBD;
    Connection conn;
    PreparedStatement ps;
    Statement stmt;
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
    
    public List<Musico> listMusicos() {
        String sql = "SELECT * FROM musicos ORDER BY nome";
        List<Musico> musicos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Musico m = new Musico();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setApelido(rs.getString("apelido"));
                m.setEmail(rs.getString("email"));
                m.setInstrumento(rs.getString("instrumento"));
                m.setTelefone(rs.getString("telefone"));
                m.setAnoIngresso(rs.getInt("ano_ingresso"));
                musicos.add(m);
            }
            return musicos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sem conexão com o banco de dados", "Sem conexão", JOptionPane.WARNING_MESSAGE); //RETIRAR
            return null;
        }
    }
    
    public List<Musico> searchMusicos(String nome) {
        String sql = "SELECT * FROM musicos WHERE nome LIKE ? ORDER BY nome";
        List<Musico> musicos = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+nome+"%");
            rs = ps.executeQuery();
            while(rs.next()) {
                Musico m = new Musico();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setApelido(rs.getString("apelido"));
                m.setEmail(rs.getString("email"));
                m.setInstrumento(rs.getString("instrumento"));
                m.setTelefone(rs.getString("telefone"));
                m.setAnoIngresso(rs.getInt("ano_ingresso"));
                musicos.add(m);
            }
            return musicos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); //RETIRAR
            return null;
        }
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM musicos WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int updateOk = ps.executeUpdate();
            if(updateOk == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean update(Musico m) {
        String sql = "UPDATE musicos SET nome = ?, apelido = ?, telefone = ?, instrumento = ?, email = ?, ano_ingresso = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getNome());
            ps.setString(2, m.getApelido());
            ps.setString(3, m.getTelefone());
            ps.setString(4, m.getInstrumento());
            ps.setString(5, m.getEmail());
            ps.setInt(6, m.getAnoIngresso());
            ps.setInt(7, m.getId());
            int updateOk = ps.executeUpdate();
            if(updateOk == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
