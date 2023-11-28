package ru.naumen.taskManager.services;

import ru.naumen.taskManager.models.User;
import ru.naumen.taskManager.repositories.UserRepository;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void getUserById(long id) {
        userRepository.findById(id);
    }
}
