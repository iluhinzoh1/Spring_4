package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User get(int series, String model) {
        String hql = "FROM User where car.model=:model AND car.series=:series";
        TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        userTypedQuery.setParameter("series", series);
        userTypedQuery.setParameter("model", model);
        return userTypedQuery.getSingleResult();
    }

}
