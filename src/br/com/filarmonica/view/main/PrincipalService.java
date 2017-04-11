package br.com.filarmonica.view.main;

import br.com.filarmonica.dao.Conexao;
import java.sql.Connection;

public class PrincipalService {
    
    private Conexao conexao = null;
    private Connection conn = null;
    
    public PrincipalService() {
        conexao = Conexao.getInstance();
        conn = conexao.getConnection();
    }
    
    public Connection checkConnection() {
        return conn;
    }
}
