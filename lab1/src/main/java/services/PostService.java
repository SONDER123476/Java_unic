package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import models.PostEntity;
import models.dto.Post;
import repositories.PostRepository;

@Stateless
public class PostService {
    @Inject
    private PostRepository postRepository;

    public Iterable<PostEntity> getAll() {
        return postRepository.findAll();
    }

    public void create(Post dto) {
        PostEntity post1 = new PostEntity(
                (dto).getId(),
                (dto).getTitle(),
                (dto).getContent(),
                (dto).getUser_id()
        );

        postRepository.persist(post1);
    }


    public void delete(int studentId) throws Exception {
        postRepository.delete(studentId);
    }

}
