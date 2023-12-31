package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;
    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User",User.class);
        return query.getResultList();
    }


    @Override
    public User getUserByCar(String carName, int carSeries) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from User as u where u.car.model = :paramModel and u.car.series = :paramSeries")
                .setParameter("paramModel", carName)
                .setParameter("paramSeries", carSeries);
        return (User) query.getSingleResult();
    }
}