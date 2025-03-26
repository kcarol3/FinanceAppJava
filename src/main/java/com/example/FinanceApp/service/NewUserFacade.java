package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.LoggerFacadeInterface;
import com.example.FinanceApp.service.base.NewUserFacadeInterface;
import org.springframework.stereotype.Service;

import java.util.List;

//Tydzień 3, Wzorzec Facade 2, łączenie tworzenia konta, usera i logowania w jeden interfejs

@Service
public class NewUserFacade implements NewUserFacadeInterface {

    private final LoggerFacadeInterface logger;
    private final UserService userService;
    private final AccountService accountService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public NewUserFacade(LoggerFacadeInterface logger, UserService userService, AccountService accountService, UserRepository userRepository, AccountRepository accountRepository) {
        this.logger = logger;
        this.userService = userService;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void saveNewUser(UserDTO user) {
        Account newAccount = accountService.createAndSaveAccount("OWN");
        User newUser = userService.save(user);
        newUser.addAccount(newAccount);
        newAccount.setUser(newUser);
        userRepository.save(newUser);
        accountRepository.save(newAccount);
        
        logger.log("[LOG] Add new User with account. Account type: OWN\n");
    }
}

// Koniec, tydzien 3, wzorzec Facade 2
