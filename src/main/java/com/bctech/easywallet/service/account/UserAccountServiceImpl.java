package com.bctech.easywallet.service.account;

import com.bctech.easywallet.events.OnSuccessRegEvent;
import com.bctech.easywallet.exception.InvalidRequestException;
import com.bctech.easywallet.model.User;
import com.bctech.easywallet.model.Wallet;
import com.bctech.easywallet.payloads.account.CreateUserAccountRequest;
import com.bctech.easywallet.repository.UserAccountRepo;
import com.bctech.easywallet.service.wallet.WalletService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepo userAccountRepo;
    private final WalletService walletService;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Long createUserAccount(CreateUserAccountRequest createUserAccountRequest, HttpServletRequest request){
        if(userExists(createUserAccountRequest.getEmail())){
            throw new InvalidRequestException("User Already Exist!");
        }
        //create user
        User user = this.createUser(createUserAccountRequest);
        //create wallet
        Wallet wallet = this.createUserWallet(createUserAccountRequest, user);
        //publish sms to user
        publisher.publishEvent(new OnSuccessRegEvent(user, wallet));
        return user.getId();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return this.userAccountRepo.findById(userId);
    }

    private Wallet createUserWallet(CreateUserAccountRequest request, User user) {
        return walletService.createWallet(request, user);
    }

    private User createUser(CreateUserAccountRequest request) {
        User user =  User.builder()
                .name(request.getName())
                .bvn(request.getBvn())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .pin(request.getPin())
                .build();
        return this.userAccountRepo.save(user);
    }

    private boolean userExists(String email) {
        return this.userAccountRepo.existsByEmail(email);
    }
}
