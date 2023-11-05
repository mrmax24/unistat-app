package app.unistat.dao.impl;

import app.unistat.dao.AbstractDao;
import app.unistat.dao.LectorDao;
import app.unistat.model.Lector;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LectorDaoImpl extends AbstractDao<Lector> implements LectorDao {
    public LectorDaoImpl(SessionFactory factory) {
        super(factory, Lector.class);
    }

    @Override
    public List<String> globalSearch(String template) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT CONCAT(l.firstName, ' ', l.lastName) FROM Lector l "
                    + "WHERE CONCAT(l.firstName, ' ', l.lastName) LIKE :template";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("template", "%" + template + "%");
            List<String> searchResults = query.list();
            return searchResults;
        }
    }
}
