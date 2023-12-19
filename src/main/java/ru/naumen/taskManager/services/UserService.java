package ru.naumen.taskManager.services;


import ru.naumen.taskManager.models.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    List<User> allUsers();

    boolean deleteUser(long id);

    User getUserByTgId(String tgId);

}
