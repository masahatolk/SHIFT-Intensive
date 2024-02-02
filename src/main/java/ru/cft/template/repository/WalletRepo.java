package ru.cft.template.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cft.template.entity.WalletEntity;

public interface WalletRepo extends CrudRepository<WalletEntity, Long> {
}
