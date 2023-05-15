package com.bctech.easywallet.service.account;

import com.bctech.easywallet.model.User;
import com.bctech.easywallet.payloads.account.CreateUserAccountRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface UserAccountService {
    Long createUserAccount(CreateUserAccountRequest createUserAccountRequest, HttpServletRequest request);

    Optional<User> findUserById(Long userId);
}
