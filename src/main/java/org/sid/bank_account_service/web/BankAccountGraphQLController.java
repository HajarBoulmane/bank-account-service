package org.sid.bank_account_service.web;

import lombok.AllArgsConstructor;
import org.sid.bank_account_service.dtos.BankAccountRequestDTO;
import org.sid.bank_account_service.dtos.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.exceptions.BankAccountNotFoundException;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountGraphQLController {

    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private CustomerRepository customerRepository;


    @QueryMapping
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccount(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank account %s not found", id)));
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }

    @MutationMapping
    public BankAccountResponseDTO addBankAccount(@Argument BankAccountRequestDTO request) {
        return bankAccountService.addAccount(request);
    }

    @MutationMapping
    public BankAccountResponseDTO updateBankAccount(@Argument String id, @Argument BankAccountRequestDTO request)
            throws BankAccountNotFoundException {
        return bankAccountService.updateAccount(id, request);
    }

    @MutationMapping
    public Boolean deleteBankAccount(@Argument String id) throws BankAccountNotFoundException {
        bankAccountService.deleteAccount(id);
        return true;
    }
}