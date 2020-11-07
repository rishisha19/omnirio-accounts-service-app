package org.omnirio.app.repository;

import org.omnirio.app.domain.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<AccountEntity, String> {
}
