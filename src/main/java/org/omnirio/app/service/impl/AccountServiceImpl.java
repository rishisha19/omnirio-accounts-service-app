package org.omnirio.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.omnirio.app.domain.AccountEntity;
import org.omnirio.app.repository.AccountsRepository;
import org.omnirio.app.service.AccountService;
import org.omnirio.app.web.rest.model.AccountR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountsRepository repository;

    @Override
    public Optional<AccountR> findById (String accountId) {
        Optional<AccountEntity> entity = repository.findById(accountId);
        return entity.map(this::toRestAccounts);
    }

    @Override
    public AccountR save (AccountR account) {
        return toRestAccounts(repository.save(toEntityAccounts(account)));
    }

    @Override
    public Optional<AccountR> update (String accountId, AccountR account) {
        Optional<AccountEntity> oldAccount = repository.findById(accountId);
        if(oldAccount.isPresent()){
            if(StringUtils.isNotBlank(account.getAccountType())) oldAccount.get().setAccountType(account.getAccountType());
            if(StringUtils.isNotBlank(account.getBranch())) oldAccount.get().setBranch(account.getBranch());
            if(StringUtils.isNotBlank(account.getCustomerId())) {
                //TODO Fetch customer name and set also set minor indicator
                oldAccount.get().setCustomerId(account.getCustomerId());
            }
            if(StringUtils.isNotBlank(account.getCustomerName())) oldAccount.get().setCustomerName(account.getCustomerName());
            return Optional.of(toRestAccounts(repository.save(oldAccount.get())));
        }
        return Optional.empty();
    }


    @Override
    public boolean deleteById (String accountId) {
        if(repository.existsById(accountId)) {
            repository.deleteById(accountId);
            return true;
        }
        return false;
    }

    private AccountR toRestAccounts (AccountEntity entity){
        return new AccountR(entity.getAccountId(), entity.getAccountType(), entity.getOpenDate().toString(),
                            entity.getCustomerId(), entity.getCustomerName(), entity.getBranch(), entity.getMinorIndicator());
    }

    private AccountEntity toEntityAccounts (AccountR rest){
        return new AccountEntity(rest.getAccountId(), rest.getAccountType(), Date.valueOf(rest.getOpenDate()),
                                 rest.getCustomerId(), rest.getCustomerName(), rest.getBranch(), rest.getMinorIndicator());
    }
}
