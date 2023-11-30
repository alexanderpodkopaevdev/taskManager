package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Role;
import ru.naumen.taskManager.models.User;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByname(String name);
}
