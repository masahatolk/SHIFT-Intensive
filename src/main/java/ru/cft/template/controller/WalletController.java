package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.template.service.WalletService;

@RestController
@RequestMapping()
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/wallet/bill/{userId}")
    public ResponseEntity getWallet(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(walletService.getWallet(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
