package com.bctech.easywallet.service.ussd;

import com.bctech.easywallet.payloads.UssdRequest;

public interface UssdService {
    String handleRequest(UssdRequest ussdRequest);
}
