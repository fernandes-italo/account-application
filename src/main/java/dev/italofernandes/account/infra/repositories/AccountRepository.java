package dev.italofernandes.account.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.italofernandes.account.domain.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
