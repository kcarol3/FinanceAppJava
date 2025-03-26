package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface NewUserFacadeInterface {
    void saveNewUser(UserDTO user);
}
