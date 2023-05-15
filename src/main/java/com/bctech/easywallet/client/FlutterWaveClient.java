package com.bctech.easywallet.client;

import com.bctech.easywallet.payloads.BaseResponse;
import com.bctech.easywallet.payloads.wallet.FlwBaseRequest;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import static com.bctech.easywallet.constants.Api.FLW_CREATE_VIRTUAL_ACCOUNT_URI;

@HttpExchange
public interface FlutterWaveClient {
    @PostExchange(value = FLW_CREATE_VIRTUAL_ACCOUNT_URI, accept = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    Mono<BaseResponse<?>> createWallet(@RequestBody FlwBaseRequest walletRequest);
}
