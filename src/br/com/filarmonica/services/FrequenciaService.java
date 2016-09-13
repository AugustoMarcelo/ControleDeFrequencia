package br.com.filarmonica.services;

import br.com.filarmonica.dao.FrequenciaDAO;
import br.com.filarmonica.models.Frequencia;
import java.util.List;

public class FrequenciaService {

    FrequenciaDAO frequenciaDao;

    public FrequenciaService() {
        frequenciaDao = new FrequenciaDAO();
    }
    
    public boolean insert(List<Frequencia> frequencias) {
        return frequenciaDao.insert(frequencias);
    }
    
    public boolean insert(Frequencia frequencia) {
        return frequenciaDao.insert(frequencia);
    }
    
    public List<Frequencia> listFrequencias() {
        return frequenciaDao.listFrequencias();
    }
}
