package br.com.filarmonica.dao;

import br.com.filarmonica.models.Pagamento;
import br.com.filarmonica.utilities.ShowMessage;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Augusto
 */
public class PagamentoDAO {

    Conexao conexaoBD;
    Connection conn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public PagamentoDAO() {
        conexaoBD = Conexao.getInstance();
        conn = conexaoBD.getConnection();
    }
    
    public boolean insert(Pagamento p) {
        String sql = "INSERT INTO pagamentos (id_musico, data, valor) VALUES (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getMusico().getId());
            ps.setDate(2, new Date(p.getData().getTime()));
            ps.setDouble(3, p.getValor());
            int updateOk = ps.executeUpdate();
            if (updateOk == 1) {
                return true;
            }
        } catch (SQLException e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
        return false;
    }
    
    public boolean update(Pagamento p) {
        return false;
    }
    
    public List<Pagamento> listPagamentos(int idMusico) {
        String sql = "SELECT * FROM pagamentos WHERE id_musico = ? ORDER BY data DESC";
        List<Pagamento> pagamentos = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idMusico);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pagamento p = new Pagamento();
                p.setId(rs.getInt("id"));
                p.setValor(rs.getDouble("valor"));
                p.setData(rs.getDate("data"));
                pagamentos.add(p);
            }
            return pagamentos;
        } catch (SQLException e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }
    
    public Double listValoresRecebidos() {
        String sql = "SELECT valor FROM pagamentos";
        Double valor = .0d;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                valor+= rs.getDouble("valor");
            }
            return valor;
        } catch (SQLException e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }
}
