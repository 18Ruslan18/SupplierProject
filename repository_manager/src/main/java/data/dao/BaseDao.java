package data.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс BaseDao родитель всех DAO классов.
 * Обертка на SQL
 *
 * @param <T> - параметр для таблицы
 */

public abstract class BaseDao<T> {

    private SessionFactory sessionFactory;
    private Class<T> type;
    private Logger logger;

    public BaseDao(SessionFactory sessionFactory, Class<T> type, Logger logger) {
        this.sessionFactory = sessionFactory;
        this.type = type;
        this.logger = logger;
    }

    public T get(final int id) {
        return withSession(new SessionCallback<T>() {
            @Override
            public T onSuccess(Session session) {
                return session.get(type, id);
            }

            @Override
            public void onFailure(Exception exception) {
                logError(exception);
            }
        });
    }

    public List<T> get() {
        return withEntityManager(new EntityManagerCallback<List<T>>() {
            @Override
            public List<T> onSuccess(EntityManager entityManager) {
                String query = "FROM " + type.getSimpleName() + " ";
                return entityManager.createQuery(query, type)
                        .getResultList();
            }

            @Override
            public void onFailure(Exception exception) {
                logError(exception);
            }
        });
    }

    public boolean create(final T model) {
        return withSession(new SessionCallback<T>() {
            @Override
            public T onSuccess(Session session) {
                session.save(model);
                return model;
            }

            @Override
            public void onFailure(Exception exception) {
                logError(exception);
            }
        }) != null;
    }

    public boolean update(final T model) {
        return withSession(new SessionCallback<T>() {
            @Override
            public T onSuccess(Session session) {
                session.update(model);
                return model;
            }

            @Override
            public void onFailure(Exception exception) {
                logError(exception);
            }
        }) != null;
    }

    public boolean remove(final T model) {
        return withSession(new SessionCallback<T>() {
            @Override
            public T onSuccess(Session session) {
                session.delete(model);
                return model;
            }

            @Override
            public void onFailure(Exception exception) {
                logError(exception);
            }
        }) != null;
    }

    protected T withSession(SessionCallback<T> callback) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            T result = callback.onSuccess(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            callback.onFailure(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }

    protected List<T> withEntityManager(EntityManagerCallback<List<T>> callback) {
        EntityManager entityManager = null;

        try {
            entityManager = sessionFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<T> result = callback.onSuccess(entityManager);
            transaction.commit();
            return result;
        } catch (Exception e) {
            callback.onFailure(e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return null;
    }

    protected void logError(Throwable throwable) {
        logger.log(Level.WARNING, throwable.getMessage(), throwable);
    }

    protected interface SessionCallback<T> {

        T onSuccess(Session session);

        void onFailure(Exception exception);
    }

    protected interface EntityManagerCallback<T> {

        T onSuccess(EntityManager entityManager);

        void onFailure(Exception exception);
    }
}
