package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.UserEntity;

import java.util.UUID;

public interface UserRepo extends CrudRepository<UserEntity, UUID> {
    UserEntity findByPhone (Long phone);
    UserEntity findByEmail (String email);
}
