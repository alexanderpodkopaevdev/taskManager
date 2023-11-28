package ru.naumen.taskManager.services;


import ru.naumen.taskManager.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void getUserById(long id);

}
