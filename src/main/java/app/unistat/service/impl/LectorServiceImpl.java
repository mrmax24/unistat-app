package app.unistat.service.impl;

import app.unistat.dao.LectorDao;
import app.unistat.model.Lector;
import app.unistat.service.LectorService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LectorServiceImpl implements LectorService {
    private final LectorDao lectorDao;

    public LectorServiceImpl(LectorDao lectorDao) {
        this.lectorDao = lectorDao;
    }

    @Override
    public Lector add(Lector lector) {
        return lectorDao.add(lector);
    }

    @Override
    public List<String> globalSearch(String template) {
        return lectorDao.globalSearch(template);
    }
}
