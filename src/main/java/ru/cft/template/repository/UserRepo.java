package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
