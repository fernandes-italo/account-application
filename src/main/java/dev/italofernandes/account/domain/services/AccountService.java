package dev.italofernandes.account.domain.services;

import dev.italofernandes.account.domain.models.Account;

public interface AccountService {

    Account find(Long accountId);

    void debit(Long accountId, Double valueOfDebit);

}
