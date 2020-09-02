package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> userList = entityManager.createQuery("from User").getResultList();
        entityManager.close();
        return userList;
    }

    @Override
    public User findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (user != null){
            entityManager.remove(user);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean saveUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User userFromDb = findByUserForNickname(user.getNickname());

        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public List<User> usergtList(Long idMin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public User findByUserForNickname(String nickname) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, nickname);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public void edit(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
