package org.sid.bank_account_service.web;


import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.exceptions.BankAccountNotFoundException;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> bankAccounts() {
        return bankAccountService.listAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public ResponseEntity<?> bankAccount(@PathVariable String id) {
        try {
            return ResponseEntity.ok(bankAccountService.getAccount(id));
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO request) {
        return bankAccountService.addAccount(request);
    }

    @PutMapping("/bankAccounts/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody BankAccountRequestDTO request) {
        try {
            return ResponseEntity.ok(bankAccountService.updateAccount(id, request));
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/bankAccounts/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            bankAccountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
