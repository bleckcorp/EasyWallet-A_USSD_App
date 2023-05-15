package com.bctech.easywallet.service.sms;

import com.bctech.easywallet.model.User;
import com.bctech.easywallet.model.Wallet;

public interface SmsService {
    void sendSmsNotification(User user, Wallet wallet);
}
