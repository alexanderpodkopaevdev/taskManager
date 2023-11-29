package ru.naumen.taskManager.services;


import ru.naumen.taskManager.models.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    void getUserById(long id);

    List<User> allUsers();

    boolean deleteUser(long id);

}
