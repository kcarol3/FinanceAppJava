package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.UserDTO;
import org.springframework.stereotype.Service;

//tydzien 7, dependency inversion 11
@Service
public interface NewUserFacadeInterface {
    void saveNewUser(UserDTO user);
}
