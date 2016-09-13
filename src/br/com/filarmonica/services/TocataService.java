package br.com.filarmonica.services;

import br.com.filarmonica.dao.TocataDAO;
import br.com.filarmonica.models.Tocata;
import java.util.List;

public class TocataService {

    private TocataDAO tocataDao;

    public TocataService() {
        tocataDao = new TocataDAO();
    }

    public boolean save(Tocata t) {
        if (t.getId() == 0) {
            return tocataDao.insert(t);
        } else {
            return tocataDao.update(t);
        }
    }
    
    public boolean delete(int id) {
        return tocataDao.delete(id);
    }
    
    public List<Tocata> listTocatas() {
        return tocataDao.listTocatas();
    }
    
    public List<Tocata> search(String local) {
        return tocataDao.listTocatas(local);
    }
}
