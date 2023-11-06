package app.unistat.dao;

import app.unistat.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> clazz;

    public AbstractDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    public T add(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + clazz.getSimpleName() + " " + t, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
