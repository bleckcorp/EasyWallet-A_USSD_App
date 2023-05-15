package com.bctech.easywallet.events;

import com.bctech.easywallet.model.User;
import com.bctech.easywallet.model.Wallet;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OnSuccessRegEvent extends ApplicationEvent {
    User user;
    Wallet wallet;

    public OnSuccessRegEvent(User user, Wallet wallet) {
        super(user);
        this.user = user;
        this.wallet = wallet;
    }
}
