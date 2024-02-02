package ru.cft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getWallet(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(walletService.getWallet(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PostMapping("/hesoyam")
    public ResponseEntity deposit(@RequestBody DepositRequest body){
        try {
            return ResponseEntity.ok(walletService.deposit(body.getUserId(), body.getAmount()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
