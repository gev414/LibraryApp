package repo;

import models.User;

import java.util.*;

public class InMemoryUserRepo implements UserRepo{
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> searchByName(String name) {
        return users.values().stream().filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
