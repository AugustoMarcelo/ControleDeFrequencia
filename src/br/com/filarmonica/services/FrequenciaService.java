package br.com.filarmonica.services;

import br.com.filarmonica.dao.FrequenciaDAO;
import br.com.filarmonica.models.Frequencia;
import br.com.filarmonica.models.ModelToPDF;
import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Tocata;
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
    
    public List<Frequencia> listFrequencias(Tocata t) {
        return frequenciaDao.listFrequencias(t);
    }
    
    public List<Musico> listMusicosToTocata(Tocata t) {
        return frequenciaDao.listMusicosToTocata(t);
    }
    
    public boolean update(List<Frequencia> frequencias) {
        return frequenciaDao.insert(frequencias);
    }
    
    public boolean delete(List<Frequencia> frequencias) {
        return frequenciaDao.delete(frequencias);
    }
    
    public boolean delete(Frequencia frequencia) {
        return frequenciaDao.delete(frequencia);
    }
    
    public List<ModelToPDF> listToPDF(Tocata t) {
        return frequenciaDao.listToPDF(t);
    }
    
    public List<ModelToPDF> listToPDF(String dataInicio, String dataFim) {
        return frequenciaDao.listToPDF(dataInicio, dataFim);
    }
}
