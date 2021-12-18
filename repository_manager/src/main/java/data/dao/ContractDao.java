package data.dao;

import data.hib.HibContract;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class ContractDao extends BaseDao<HibContract> {

    public ContractDao(SessionFactory sessionFactory) {
        super(sessionFactory, HibContract.class, Logger.getLogger(ContractDao.class.getName()));
    }

    public List<HibContract> find(final String search) {
        return withEntityManager(new EntityManagerCallback<List<HibContract>>() {
            @Override
            public List<HibContract> onSuccess(EntityManager entityManager) {
                String query = "FROM HibContract d WHERE brand like :search OR business like :search OR rent like :search OR director like :search OR ondate like :search OR offdate like :search";
                TypedQuery<HibContract> typedQuery = entityManager.createQuery(query, HibContract.class)
                        .setParameter("search", '%' + search + '%');
                return typedQuery.getResultList();
            }

            @Override
            public void onFailure(Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}
