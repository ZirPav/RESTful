package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Role> allRoles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Role> roleList = entityManager.createQuery("from Role").getResultList();
        entityManager.close();
        return roleList;
    }

    @Override
    public Role findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return role;
    }

    @Override
    public void deleteRole(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Role role = entityManager.find(Role.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean saveRole(Role role) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

}
