package dev.italofernandes.account.domain.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person customer;

    private Double balance;

    public Boolean debit(Double valueOfDebit) {

        if (this.getBalance() < valueOfDebit)
            return false;

        Double newBalance = this.getBalance() - valueOfDebit;
        this.setBalance(newBalance);

        return true;
    }

}
