package ru.cft.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.exception.UserAgeIsNotAllowed;
import ru.cft.template.exception.UserAlreadyExistException;
import ru.cft.template.exception.UserNotFoundException;
import ru.cft.template.dto.User;
import ru.cft.template.repository.UserRepo;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException, UserAgeIsNotAllowed {
        if (user.getAge() < 18 || user.getAge() > 100) {
            throw new UserAgeIsNotAllowed("Недопустимый возраст пользователя");
        }

        if (userRepo.findByPhone(user.getPhone()) != null) {
            throw new UserAlreadyExistException("Такой номер уже занят другим пользователем");
        }

        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("Такой email уже занят другим пользователем");
        }
        return userRepo.save(user);
    }

    public User getOneUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        return User.toModel(user);
    }

    public User updateUserByFields(Long id, Map<String, Object> fields) {
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
