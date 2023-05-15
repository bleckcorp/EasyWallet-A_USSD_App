package com.bctech.easywallet.controller;

import com.bctech.easywallet.payloads.BaseResponse;
import com.bctech.easywallet.payloads.account.CreateUserAccountRequest;
import com.bctech.easywallet.service.account.UserAccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/create-account")
    public ResponseEntity<BaseResponse<Long>> createUserAccount(@Valid @RequestBody CreateUserAccountRequest userAccountRequest,
                                                          HttpServletRequest request){
        Long userId = this.userAccountService.createUserAccount(userAccountRequest, request);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.CREATED.name(), "Account Created Successfully!", userId));
    }
}
