package ru.cft.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.dto.GetWalletResponse;
import ru.cft.template.entity.WalletEntity;
import ru.cft.template.repository.WalletRepo;

import java.time.LocalDate;

@Service
public class WalletService {

    @Autowired
    private WalletRepo walletRepo;

    public GetWalletResponse getWallet(Long userId) {
        WalletEntity wallet = walletRepo.findById(userId).get();
        return GetWalletResponse.toModel(wallet);
    }

    public WalletEntity createWallet() {
        WalletEntity wallet = new WalletEntity();

        wallet.setAmount(0L);
        wallet.setLastUpdate(LocalDate.now());
        walletRepo.save(wallet);

        return walletRepo.save(wallet);
    }
}
