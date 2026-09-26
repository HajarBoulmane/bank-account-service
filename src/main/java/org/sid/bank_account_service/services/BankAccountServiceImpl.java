package org.sid.bank_account_service.services;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.exceptions.BankAccountNotFoundException;
import org.sid.bank_account_service.mappers.BankAccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO request) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(request.getBalance())
                .currency(request.getCurrency())
                .type(request.getType())
                .build();
        return bankAccountMapper.fromEntity(bankAccountRepository.save(bankAccount));
    }

    @Override
    public List<BankAccountResponseDTO> listAccounts() {
        return bankAccountRepository.findAll().stream()
                .map(bankAccountMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO getAccount(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(String.format("Bank account %s not found", id)));
        return bankAccountMapper.fromEntity(bankAccount);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO request) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(String.format("Bank account %s not found", id)));
        if (request.getCurrency() != null) bankAccount.setCurrency(request.getCurrency());
        if (request.getType() != null) bankAccount.setType(request.getType());
        bankAccount.setBalance(request.getBalance());
        return bankAccountMapper.fromEntity(bankAccountRepository.save(bankAccount));
    }

    @Override
    public void deleteAccount(String id) throws BankAccountNotFoundException {
        if (!bankAccountRepository.existsById(id)) {
            throw new BankAccountNotFoundException(String.format("Bank account %s not found", id));
        }
        bankAccountRepository.deleteById(id);
    }
}
