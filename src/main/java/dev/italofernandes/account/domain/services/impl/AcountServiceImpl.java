package dev.italofernandes.account.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.italofernandes.account.domain.exceptions.AccountNotFoundException;
import dev.italofernandes.account.domain.exceptions.AccountWithoutBalanceException;
import dev.italofernandes.account.domain.models.Account;
import dev.italofernandes.account.domain.services.AccountService;
import dev.italofernandes.account.infra.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Value("${exceptions.account.not-found-message}")
    private String accountNotFoundMessage;

    @Value("${exceptions.account.not-found-description}")
    private String accountNotFoundDescription;

    @Value("${exceptions.account.without-balance-message}")
    private String accountWithoutBalanceMessage;

    @Value("${exceptions.account.without-balance-description}")
    private String accountWithoutBalanceDescription;

    @Override
    public Account find(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty()) {
            throw new AccountNotFoundException(
                    accountNotFoundMessage,
                    accountNotFoundDescription);
        }

        return account.get();
    }

    @Override
    public void debit(Long accountId, Double valueOfDebit) {
        Account account = this.find(accountId);

        Boolean debited = account.debit(valueOfDebit);

        if (!debited) {
            throw new AccountWithoutBalanceException(
                    accountWithoutBalanceMessage,
                    accountWithoutBalanceDescription);
        }

        accountRepository.save(account);

    }

}
