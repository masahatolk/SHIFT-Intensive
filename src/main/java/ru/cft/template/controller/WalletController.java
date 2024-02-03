package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.dto.DepositRequest;
import ru.cft.template.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping()
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/wallet/bill/{userId}")
    public Object getWallet(@PathVariable UUID userId) {
        try {
            return walletService.getWallet(userId);
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }

    @PostMapping("/hesoyam")
    public Object deposit(@RequestBody DepositRequest body){
        try {
            return walletService.deposit(body.getUserId(), body.getAmount());
        } catch (Exception e) {
            return "Произошла ошибка";
        }
    }
}
