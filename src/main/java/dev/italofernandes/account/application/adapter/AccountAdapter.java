package dev.italofernandes.account.application.adapter;

import dev.italofernandes.account.application.dto.response.AccountBalanceResponse;
import dev.italofernandes.account.domain.models.Account;

public class AccountAdapter {

    public static AccountBalanceResponse toDtoBalance(Account account) {
        return new AccountBalanceResponse(account.getId(), account.getBalance());
    }

}
