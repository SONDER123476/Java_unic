package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.UserEntity;

import javax.enterprise.inject.Any;

@Any
@Stateless
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void persist(UserEntity entity) {
        em.persist(entity);
    }

    public Iterable<UserEntity> findAll() {
        return em.createQuery("select i from UserEntity i", UserEntity.class).getResultList();
    }

    public UserEntity findObject(UserEntity entity) {
        return em.createQuery("select i from UserEntity i " +
                                "where i.id = :id and i.username = :username ",
                        UserEntity.class)
                .setParameter("id", entity.getId())
                .setParameter("username", entity.getUsername())
                .getSingleResult();
    }

    public UserEntity findById(int id) {
        return em.createQuery("select i from UserEntity i where i.id = :id", UserEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        UserEntity entity = em.find(UserEntity.class, id);
        em.remove(entity);
    }
}
