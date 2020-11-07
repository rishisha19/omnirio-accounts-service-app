package org.omnirio.app.service;

import org.omnirio.app.web.rest.model.AccountR;

import java.util.Optional;

public interface AccountService {

    Optional<AccountR> findById (String accountId);
    AccountR save (AccountR account);

    Optional<AccountR> update(String accountId, AccountR newData);

    boolean deleteById (String accountId);

}
