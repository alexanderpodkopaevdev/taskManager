package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByname(String name);

    User findByTgID(String tgID);
}
