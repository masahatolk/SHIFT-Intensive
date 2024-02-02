package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.SessionEntity;

import java.util.UUID;

public interface SessionRepo extends CrudRepository<SessionEntity, UUID> {
    SessionEntity findByToken (String token);
}
