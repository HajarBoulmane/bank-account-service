package org.sid.bank_account_service.services;

import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO request);
    List<BankAccountResponseDTO> listAccounts();
    BankAccountResponseDTO getAccount(String id) throws BankAccountNotFoundException;
    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO request) throws BankAccountNotFoundException;
    void deleteAccount(String id) throws BankAccountNotFoundException;

}
