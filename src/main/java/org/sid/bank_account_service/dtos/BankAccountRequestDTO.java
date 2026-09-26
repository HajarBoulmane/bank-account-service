package org.sid.bank_account_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank_account_service.entities.AccountType;

@Data @NoArgsConstructor @AllArgsConstructor  @Builder
public class BankAccountRequestDTO {

    private double balance;
    private String currency;
    private AccountType type;

}
