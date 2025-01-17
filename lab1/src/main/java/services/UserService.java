package services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import models.UserEntity;
import models.dto.User;
import repositories.UserRepository;

@Stateless
public class UserService {
    @Inject
    private UserRepository userRepository;

    public Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public void create(User dto) {
        UserEntity user1 = new UserEntity(
                dto.getId(),
                dto.getUsername()
        );
        userRepository.persist(user1);
    }

    public void delete(int id) throws Exception {
        userRepository.delete(id);
    }

}