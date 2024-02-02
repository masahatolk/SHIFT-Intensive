package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.WalletEntity;

import java.util.UUID;

public interface WalletRepo extends CrudRepository<WalletEntity, UUID> {
}
