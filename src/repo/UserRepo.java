package repo;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    void save(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    List<User> searchByName(String name);
}
