package br.com.filarmonica.dao;

import br.com.filarmonica.models.Frequencia;
import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Tocata;
import br.com.filarmonica.utilities.ShowMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrequenciaDAO {

    Conexao conexao;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Statement stmt;

    public FrequenciaDAO() {
        conexao = Conexao.getInstance();
        conn = conexao.getConnection();
    }

    public boolean insert(Frequencia frequencia) {
        String sql = "INSERT INTO frequencia (id_musico, id_tocata, presenca) VALUES (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, frequencia.getMusico().getId());
            ps.setInt(2, frequencia.getTocata().getId());
            ps.setInt(3, (frequencia.isPresenca() ? 1 : 0));
            int updateOk = ps.executeUpdate();
            return updateOk == 1;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
    }

    public boolean insert(List<Frequencia> frequencias) {
        String sql = "INSERT INTO frequencia (id_musico, id_tocata, presenca) VALUES (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            for (Frequencia f : frequencias) {
                if (checksIfIsNotInTheList(f)) {
                    ps.setInt(1, f.getMusico().getId());
                    ps.setInt(2, f.getTocata().getId());
                    ps.setInt(3, (f.isPresenca() ? 1 : 0));
                    if (ps.executeUpdate() != 1) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("erro insert");
            ShowMessage.msgError(e.toString());
            return false;
        }
    }

    public List<Frequencia> listFrequencias() {
        List<Frequencia> frequencias = new ArrayList<>();
        String sql = "SELECT m.id AS idMusico, m.nome AS nome, m.email AS email, m.apelido AS apelido, m.telefone AS telefone, m.instrumento AS instrumento, m.ano_ingresso AS ano_ingresso, t.id AS idTocata, t.local AS local, t.data AS data, t.horario AS horario, t.comentarios AS comentarios, f.presenca AS presenca FROM Frequencia f, musicos m, Tocatas t WHERE f.id_musico = m.id AND f.id_tocata = t.id;";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Frequencia f = new Frequencia();
                Musico m = new Musico();
                Tocata t = new Tocata();
                m.setId(rs.getInt("idMusico"));
                m.setNome(rs.getString("nome"));
                m.setEmail(rs.getString("email"));
                m.setApelido(rs.getString("apelido"));
                m.setInstrumento(rs.getString("instrumento"));
                m.setAnoIngresso(rs.getInt("ano_ingresso"));
                m.setTelefone(rs.getString("telefone"));
                f.setMusico(m);
                t.setId(rs.getInt("idTocata"));
                t.setComentarios(rs.getString("comentarios"));
                t.setHorario(rs.getString("horario"));
                t.setLocal(rs.getString("local"));
                t.setData(new Date(rs.getDate("data").getTime()));
                f.setTocata(t);
                f.setPresenca((rs.getInt("presenca") == 1));
                frequencias.add(f);
            }
            return frequencias;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }

    /* RETORNA FALSO SE A FREQUÊNCIA PASSADA JÁ EXISTIR NO BANCO*/
    private boolean checksIfIsNotInTheList(Frequencia f) {
        String sql = "SELECT COUNT(*) AS quantidade FROM frequencia WHERE id_tocata = ? AND id_musico = ?";
        PreparedStatement prepare = null;
        int quantidade = 0;
        try {
            prepare = conn.prepareStatement(sql);
            prepare.setInt(1, f.getTocata().getId());
            prepare.setInt(2, f.getMusico().getId());
            rs = prepare.executeQuery();
            if (rs.next()) {
                quantidade = rs.getInt("quantidade");
            }
            return quantidade == 0;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        } finally {
            try {
                prepare.close();
            } catch (SQLException ex) {
                return false;
            }
        }
    }

    public List<Frequencia> listFrequencias(Tocata tocata) {
        List<Frequencia> frequencias = new ArrayList<>();
        String sql = "SELECT m.id AS idMusico, m.nome AS nome, m.email AS email, m.apelido AS apelido, m.telefone AS telefone, m.instrumento AS instrumento, m.ano_ingresso AS ano_ingresso, t.id AS idTocata, t.local AS local, t.data AS data, t.horario AS horario, t.comentarios AS comentarios, f.presenca AS presenca FROM Frequencia f, musicos m, Tocatas t WHERE f.id_musico = m.id AND f.id_tocata = t.id AND ? = f.id_tocata";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tocata.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Frequencia f = new Frequencia();
                Musico m = new Musico();
                Tocata t = new Tocata();
                m.setId(rs.getInt("idMusico"));
                m.setNome(rs.getString("nome"));
                m.setEmail(rs.getString("email"));
                m.setApelido(rs.getString("apelido"));
                m.setInstrumento(rs.getString("instrumento"));
                m.setAnoIngresso(rs.getInt("ano_ingresso"));
                m.setTelefone(rs.getString("telefone"));
                f.setMusico(m);
                t.setId(rs.getInt("idTocata"));
                t.setComentarios(rs.getString("comentarios"));
                t.setHorario(rs.getString("horario"));
                t.setLocal(rs.getString("local"));
                t.setData(new Date(rs.getDate("data").getTime()));
                f.setTocata(t);
                f.setPresenca((rs.getInt("presenca") == 1));
                frequencias.add(f);
            }
            return frequencias;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }

    /* MÉTODO UTILIZADO PARA PREENCHER O COMBO DE MÚSICOS COM SOMENTE COM OS QUE NÃO ESTÃO PRESENTES NA TOCATA SELECIONADA*/
    public List<Musico> listMusicosToTocata(Tocata tocata) {
        List<Musico> musicos = new ArrayList<>();
        String sql = "SELECT musicos.* FROM musicos WHERE id NOT IN (SELECT id_musico FROM frequencia WHERE id_tocata = ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tocata.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Frequencia f = new Frequencia();
                Musico m = new Musico();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setEmail(rs.getString("email"));
                m.setApelido(rs.getString("apelido"));
                m.setInstrumento(rs.getString("instrumento"));
                m.setAnoIngresso(rs.getInt("ano_ingresso"));
                m.setTelefone(rs.getString("telefone"));
                musicos.add(m);
            }
            return musicos;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return null;
        }
    }

    public boolean delete(List<Frequencia> frequencias) {
        String sql = "DELETE FROM frequencia WHERE id_tocata = ? AND id_musico = ?";
        try {
            for (Frequencia f : frequencias) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, f.getTocata().getId());
                ps.setInt(2, f.getMusico().getId());
                int deleteOk = ps.executeUpdate();
                if (deleteOk != 1) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
    }

    public boolean delete(Frequencia f) {
        String sql = "DELETE FROM frequencia WHERE id_tocata = ? AND id_musico = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, f.getTocata().getId());
            ps.setInt(2, f.getMusico().getId());
            int deleteOk = ps.executeUpdate();
            if (deleteOk != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            ShowMessage.msgError(e.toString());
            return false;
        }
    }
}
