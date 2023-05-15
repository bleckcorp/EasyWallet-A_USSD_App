package com.bctech.easywallet.service.transaction;

import com.bctech.easywallet.enums.TransactionType;
import com.bctech.easywallet.exception.InvalidRequestException;
import com.bctech.easywallet.model.Transaction;
import com.bctech.easywallet.model.User;
import com.bctech.easywallet.model.Wallet;
import com.bctech.easywallet.repository.TransactionRepo;
import com.bctech.easywallet.service.account.UserAccountService;
import com.bctech.easywallet.service.wallet.WalletService;
import com.bctech.easywallet.utils.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.bctech.easywallet.enums.TransactionType.DEPOSIT;
import static com.bctech.easywallet.enums.TransactionType.WITHDRAWAL;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final WalletService walletService;
    private final UserAccountService userAccountService;
    private final TransactionRepo transactionRepo;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void initiateDeposit(Long userId, BigDecimal amount) {
        Wallet wallet = this.walletService.deposit(userId, amount);
        saveTransaction(userId, amount, wallet, DEPOSIT);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void initiateWithdrawal(Long userId, BigDecimal amount) {
        Wallet wallet = this.walletService.withdraw(userId,amount);
        saveTransaction(userId, amount, wallet, WITHDRAWAL);
    }

    @Override
    public BigDecimal checkBalance(Long userId) {
        return this.walletService.getBalance(userId);
    }


    private void saveTransaction(Long userId, BigDecimal amount, Wallet wallet, TransactionType transactionType) {
        User user = userAccountService.findUserById(userId)
                .orElseThrow(()-> new InvalidRequestException("User does Not Exist"));
        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .user(user)
                .wallet(wallet)
                .trxRef(Util.generateUniqueCode())
                .amount(amount)
                .build();
        this.transactionRepo.save(transaction);
    }
}
