package services;

import models.User;
import repo.UserRepo;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void registerUser(User user){
        userRepo.save(user);
    }

    public Optional<User> getUserById(int id){
        return userRepo.findById(id);
    }

    public List<User> listAllUsers(){
        return userRepo.findAll();
    }

    public List<User> searchUsersByName(String name){
        return userRepo.searchByName(name);
    }
}
