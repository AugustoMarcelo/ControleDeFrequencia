package br.com.filarmonica.services;

import br.com.filarmonica.dao.PagamentoDAO;
import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Pagamento;
import java.util.List;

/**
 *
 * @author Marcelo Augusto
 */
public class PagamentoService {
    
    private PagamentoDAO pagamentoDAO;
    private MusicoService mService;
    private FrequenciaService fService;
    
    public PagamentoService() {
        pagamentoDAO = new PagamentoDAO();
        mService = new MusicoService();
        fService = new FrequenciaService();
    }
    
    public boolean save(Pagamento p) {
        if (p.getId() == 0) {
            return pagamentoDAO.insert(p);
        } else {
            return pagamentoDAO.update(p);
        }        
    }
    
    public Musico searchMusico(int id) {
        return mService.search(id);
    }
    
    public List<Pagamento> listPagamentos(int idMusico) {
        return pagamentoDAO.listPagamentos(idMusico);
    }
    
    public Double listValoresRecebidos() {
        return pagamentoDAO.listValoresRecebidos();
    }
    
    public Double listAreceber(int idMusico) {
        return fService.aReceber(idMusico);
    }
    
    public Double listAreceber() {
        return fService.aReceber();
    }
}
