package org.omnirio.app.web.rest;

import org.apache.commons.lang3.StringUtils;
import org.omnirio.app.service.AccountService;
import org.omnirio.app.web.rest.model.AccountR;
import org.omnirio.app.web.rest.model.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
    MediaType.APPLICATION_JSON_VALUE)
public class AccountServiceController {

    @Autowired
    AccountService accountsService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccounts (@PathVariable @Valid String accountId) {
        Optional<AccountR> account = accountsService.findById(accountId);
        return account.<ResponseEntity<Object>>map(accountR -> new ResponseEntity<>(accountR, HttpStatus.FOUND))
            .orElseGet(
                () -> new ResponseEntity<>(new ApiError(String.format("Account with id `%s` not found.", accountId),
                                                        Collections.emptyList()),
                                           HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public ResponseEntity<AccountR> createAccounts (@RequestBody AccountR account){

        AccountR accountEntity = accountsService.save(account);
        if(StringUtils.isNoneBlank(accountEntity.getAccountId()))
            return new ResponseEntity<>(accountEntity, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{accountsId}")
    public ResponseEntity<Object> updateAccounts (@PathVariable @Valid String accountsId,
                                                  @RequestBody AccountR account){

        Optional<AccountR> updated = accountsService.update(accountsId, account);
        return updated.<ResponseEntity<Object>>map(accountR -> new ResponseEntity<>(accountR, HttpStatus.OK))
            .orElseGet(
            () -> new ResponseEntity<>(String.format("Account with id %s not found", accountsId),
                                       HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{accountsId}")
    public ResponseEntity<Object> removeAccounts (@Valid String accountsId){

        if(!accountsService.findById(accountsId).isPresent())
            return new ResponseEntity<>(String.format("Account with id %s not found",accountsId), HttpStatus.NOT_FOUND);

        accountsService.deleteById(accountsId);
        return new ResponseEntity<>(String.format("Account with id %s successfully deleted",accountsId), HttpStatus.OK);
    }


}
