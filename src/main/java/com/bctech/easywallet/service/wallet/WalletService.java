package com.bctech.easywallet.service.wallet;

import com.bctech.easywallet.model.User;
import com.bctech.easywallet.model.Wallet;
import com.bctech.easywallet.payloads.BaseRequest;

import java.math.BigDecimal;

public interface WalletService {

    Wallet createWallet(BaseRequest baseRequest, User user);

    Wallet deposit(Long userId, BigDecimal amount);
    Wallet withdraw(Long userId, BigDecimal amount);

    BigDecimal getBalance(Long userId);
}
