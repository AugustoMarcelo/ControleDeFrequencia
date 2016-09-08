package br.com.filarmonica.services;

import br.com.filarmonica.dao.MusicoDAO;
import br.com.filarmonica.models.Musico;

public class MusicoService {

    MusicoDAO musicoDao;
    
    public MusicoService() {
        musicoDao = new MusicoDAO();
    }
    
    public boolean save(Musico m) {
        return musicoDao.insert(m);
    }
    
}
