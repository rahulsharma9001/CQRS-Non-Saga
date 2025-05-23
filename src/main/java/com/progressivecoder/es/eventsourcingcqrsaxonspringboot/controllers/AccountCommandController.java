package com.progressivecoder.es.eventsourcingcqrsaxonspringboot.controllers;

import com.progressivecoder.es.eventsourcingcqrsaxonspringboot.dto.commands.AccountCreateDTO;
import com.progressivecoder.es.eventsourcingcqrsaxonspringboot.dto.commands.MoneyCreditDTO;
import com.progressivecoder.es.eventsourcingcqrsaxonspringboot.dto.commands.MoneyDebitDTO;
import com.progressivecoder.es.eventsourcingcqrsaxonspringboot.services.commands.AccountCommandService;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/bank-accounts")
@Tag(name = "Account Commands", description = "Account Commands Related Endpoints")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping
    public CompletableFuture<String> createAccount(@RequestBody AccountCreateDTO accountCreateDTO){
        return accountCommandService.createAccount(accountCreateDTO);
    }

    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                          @RequestBody MoneyCreditDTO moneyCreditDTO){
        return accountCommandService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                           @RequestBody MoneyDebitDTO moneyDebitDTO){
        return accountCommandService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }
}
