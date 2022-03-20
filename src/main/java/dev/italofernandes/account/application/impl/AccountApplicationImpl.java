package dev.italofernandes.account.application.impl;

import org.springframework.stereotype.Component;

import dev.italofernandes.account.application.AccountApplication;
import dev.italofernandes.account.application.adapter.AccountAdapter;
import dev.italofernandes.account.application.dto.request.AccountDebitRequest;
import dev.italofernandes.account.application.dto.response.AccountBalanceResponse;
import dev.italofernandes.account.application.dto.response.AccountDebitResponse;
import dev.italofernandes.account.domain.models.Account;
import dev.italofernandes.account.domain.services.AccountService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountApplicationImpl implements AccountApplication {

    private final AccountService accountService;

    @Override
    public AccountBalanceResponse getBalance(Long accountId) {

        Account account = accountService.find(accountId);

        return AccountAdapter.toDtoBalance(account);
    }

    @Override
    public AccountDebitResponse debit(Long accountId, AccountDebitRequest accountDebitRequest) {

        accountService.debit(accountId, accountDebitRequest.getValueOfDebit());

        return new AccountDebitResponse(true);
    }

}
