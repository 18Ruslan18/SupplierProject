package data.dao;

import data.hib.HibRoom;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

public class RoomDao extends BaseDao<HibRoom> {

    public RoomDao(SessionFactory sessionFactory) {
        super(sessionFactory, HibRoom.class, Logger.getLogger(RoomDao.class.getName()));
    }

    public List<HibRoom> find(final String search) {
        return withEntityManager(new EntityManagerCallback<List<HibRoom>>() {
            @Override
            public List<HibRoom> onSuccess(EntityManager entityManager) {
                String query = "FROM HibRoom d WHERE space like :search OR company like :search OR temp like :search OR goods like :search";
                TypedQuery<HibRoom> typedQuery = entityManager.createQuery(query, HibRoom.class)
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
