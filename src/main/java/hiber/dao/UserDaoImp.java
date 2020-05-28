package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
      Session currentSession = sessionFactory.getCurrentSession();
      currentSession.save(user.getCar());
      currentSession.save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      Session currentSession = sessionFactory.getCurrentSession();
      TypedQuery<User> query= currentSession.createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(int series) {
      Session session = sessionFactory.getCurrentSession();
      Query query = session.createQuery("from User where car.series = :paramName");
      query.setParameter("paramName", 1);
      return (User)query.uniqueResult();
   }

}
