package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.SessionEntity;

public interface SessionRepo extends CrudRepository<SessionEntity, Long> {
    SessionEntity findByToken (String token);
}
