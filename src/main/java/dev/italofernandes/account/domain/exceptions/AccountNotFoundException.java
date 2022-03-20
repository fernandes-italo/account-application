package dev.italofernandes.account.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {

    private String description;

    public AccountNotFoundException() {
        super();
    }

    public AccountNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
