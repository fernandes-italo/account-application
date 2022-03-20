package dev.italofernandes.account.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.italofernandes.account.application.AccountApplication;
import dev.italofernandes.account.application.dto.request.AccountDebitRequest;
import dev.italofernandes.account.application.dto.response.AccountBalanceResponse;
import dev.italofernandes.account.application.dto.response.AccountDebitResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountApplication accountApplication;

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<AccountBalanceResponse> getBalance(@PathVariable Long accountId) {

        AccountBalanceResponse accountBalanceResponse = accountApplication.getBalance(accountId);

        return ResponseEntity.ok(accountBalanceResponse);
    }

    @PostMapping("/debit/{accountId}")
    public ResponseEntity<AccountDebitResponse> debit(@PathVariable Long accountId,
            @RequestBody AccountDebitRequest accountDebitRequest) {

        AccountDebitResponse accountDebitResponse = accountApplication.debit(accountId, accountDebitRequest);

        return ResponseEntity.ok(accountDebitResponse);

    }

}
