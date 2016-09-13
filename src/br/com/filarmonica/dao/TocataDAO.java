package br.com.filarmonica.dao;

import br.com.filarmonica.models.Tocata;
import br.com.filarmonica.utilities.ShowMessage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TocataDAO {

    Conexao conexao;
    Connection conn;
    PreparedStatement ps;
    Statement stmt;
    ResultSet rs;
    
    public TocataDAO() {
        conexao = Conexao.getInstance();
        conn = conexao.getConnection();
    }
    
    public boolean insert(Tocata t) {
        String sql = "INSERT INTO tocatas (comentarios, data, horario, local) VALUES(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getComentarios());
            ps.setDate(2, new Date(t.getData().getTime())); //PEGA O TEMPO EM MILISSEGUNDOS
            ps.setString(3, t.getHorario());
            ps.setString(4, t.getLocal());
            int updateOk = ps.executeUpdate();
            if(updateOk == 1) {
                return true;
            }
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
        return false;
    }
    
    public boolean update(Tocata t) {
        String sql = "UPDATE tocatas SET comentarios = ?, data = ?, horario = ?, local = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, t.getComentarios());
            ps.setDate(2, new Date(t.getData().getTime()));
            ps.setString(3, t.getHorario());
            ps.setString(4, t.getLocal());
            ps.setInt(5, t.getId());
            int updateOk = ps.executeUpdate();
            if(updateOk == 1) {
                return true;
            }
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM tocatas WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int updateOk = ps.executeUpdate();
            if(updateOk == 1) {
                return true;
            }
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
        return false;
    }
    
    public List<Tocata> listTocatas() {
        List<Tocata> tocatas = new ArrayList<>();
        String sql = "SELECT * FROM tocatas ORDER BY data DESC";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Tocata t = new Tocata();
                t.setId(rs.getInt("id"));
                t.setLocal(rs.getString("local"));
                t.setComentarios(rs.getString("comentarios"));
                t.setData(rs.getDate("data"));
                t.setHorario(rs.getString("horario"));
                tocatas.add(t);
            }
            return tocatas;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }
    
    public List<Tocata> listTocatas(String local) {
        List<Tocata> tocatas = new ArrayList<>();
        String sql = "SELECT * FROM tocatas WHERE local LIKE ? ORDER BY data DESC";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+local+"%");
            rs = ps.executeQuery();
            while(rs.next()) {
                Tocata t = new Tocata();
                t.setId(rs.getInt("id"));
                t.setLocal(rs.getString("local"));
                t.setComentarios(rs.getString("comentarios"));
                t.setData(rs.getDate("data"));
                t.setHorario(rs.getString("horario"));
                tocatas.add(t);
            }
            return tocatas;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }
}
