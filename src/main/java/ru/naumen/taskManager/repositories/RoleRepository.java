package ru.naumen.taskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.naumen.taskManager.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
