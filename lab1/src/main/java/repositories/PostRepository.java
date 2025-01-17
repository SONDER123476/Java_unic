package repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import models.PostEntity;

@Stateless
public class PostRepository {
    @PersistenceContext
    private EntityManager em;

    public void persist(PostEntity entity) {
        em.persist(entity);
    }

    public Iterable<PostEntity> findAll() {
        return em.createQuery("select i from PostEntity i", PostEntity.class).getResultList();
    }

    public PostEntity findObject(PostEntity post) {
        return em.createQuery("select i from PostEntity i " +
                                "where i.id = :id and i.title = :title " +
                                "and i.content = :content " +
                                "and i.user_id = :user_id",
                        PostEntity.class)
                .setParameter("id", post.getId())
                .setParameter("title", post.getTitle())
                .setParameter("content", post.getContent())
                .setParameter("user_id", post.getUser_id())
                .getSingleResult();
    }

    public PostEntity findById(int id) {
        return em.createQuery("select i from PostEntity i where i.id = :id", PostEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void delete(int id) {
        PostEntity entity = em.find(PostEntity.class, id);
        em.remove(entity);
    }
}
