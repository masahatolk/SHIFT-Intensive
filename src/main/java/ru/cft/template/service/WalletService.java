package ru.cft.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.DepositResponse;
import ru.cft.template.dto.GetWalletResponse;
import ru.cft.template.entity.UserEntity;
import ru.cft.template.entity.WalletEntity;
import ru.cft.template.exception.ServiceException;
import ru.cft.template.repository.UserRepo;
import ru.cft.template.repository.WalletRepo;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private UserRepo userRepo;

    public GetWalletResponse getWallet(UUID userId) {
        WalletEntity wallet = userRepo.findById(userId).get().getWallet();
        GetWalletResponse response = GetWalletResponse.toModel(wallet);
        response.setUserId(userId);
        return response;
    }

    public WalletEntity createWallet() {
        WalletEntity wallet = new WalletEntity();

        wallet.setAmount(0L);
        wallet.setLastUpdate(LocalDate.now());
        walletRepo.save(wallet);

        return walletRepo.save(wallet);
    }

    public DepositResponse deposit(UUID userId, Long amount) throws Exception {
        if(amount > 1000000){
            throw new Exception("Недопустимое количество копеек");
        }

        UserEntity user = userRepo.findById(userId).get();

        if(user == null){
            throw new ServiceException("Пользователь с таким id не найден");
        }
        WalletEntity wallet = user.getWallet();
        wallet.setAmount(wallet.getAmount() + amount);
        wallet.setLastUpdate(LocalDate.now());
        walletRepo.save(wallet);

        return DepositResponse.toModel(wallet, userId);
    }
}
