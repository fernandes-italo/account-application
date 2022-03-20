package dev.italofernandes.account.application;

import dev.italofernandes.account.application.dto.request.AccountDebitRequest;
import dev.italofernandes.account.application.dto.response.AccountBalanceResponse;
import dev.italofernandes.account.application.dto.response.AccountDebitResponse;

public interface AccountApplication {

    AccountBalanceResponse getBalance(Long accountId);

    AccountDebitResponse debit(Long accountId, AccountDebitRequest accountDebitRequest);

}
