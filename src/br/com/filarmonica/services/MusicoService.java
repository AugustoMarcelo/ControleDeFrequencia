package br.com.filarmonica.services;

import br.com.filarmonica.dao.MusicoDAO;
import br.com.filarmonica.models.Musico;
import java.util.List;

public class MusicoService {

    MusicoDAO musicoDao;

    public MusicoService() {
        musicoDao = new MusicoDAO();
    }

    public boolean save(Musico m) {
        if (m.getId() == 0) {
            return musicoDao.insert(m);
        } else {
            return musicoDao.update(m);
        }
    }

    public List<Musico> listMusicos() {
        return musicoDao.listMusicos();
    }

    public boolean delete(int id) {
        return musicoDao.delete(id);
    }
    
    public List<Musico> search(String nomeMusico) {
        return musicoDao.searchMusicos(nomeMusico);
    }
}
