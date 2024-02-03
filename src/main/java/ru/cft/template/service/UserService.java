package ru.cft.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import ru.cft.template.dto.User;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.entity.WalletEntity;
import ru.cft.template.exception.UserAlreadyExistException;
import ru.cft.template.exception.ServiceException;
import ru.cft.template.repository.UserRepo;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WalletService walletService;

    public void registerUser(UserEntity user) throws UserAlreadyExistException, ServiceException {
        if (user.getAge() < 18 || user.getAge() > 100) {
            throw new ServiceException("Недопустимый возраст пользователя");
        }

        if (userRepo.findByPhone(user.getPhone()) != null) {
            throw new UserAlreadyExistException("Такой номер уже занят другим пользователем");
        }

        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("Такой email уже занят другим пользователем");
        }

        WalletEntity wallet = walletService.createWallet();

        user.setWallet(wallet);
        user.setRegistrationDate(LocalDate.now());
        user.setLastUpdateDate(LocalDate.now());

        userRepo.save(user);
    }

    public User getOneUser(UUID id) throws ServiceException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new ServiceException("Пользователь с таким id не найден");
        }
        return User.toModel(user);
    }

    public User updateUserByFields(UUID id, Map<String, Object> fields) {
        UserEntity existingUser = userRepo.findById(id).get();

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(UserEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingUser, value);
        });

        existingUser.setLastUpdateDate(LocalDate.now());
        userRepo.save(existingUser);
        return User.toModel(existingUser);
    }
}
