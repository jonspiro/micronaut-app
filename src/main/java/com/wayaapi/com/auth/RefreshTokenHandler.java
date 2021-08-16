package com.wayaapi.com.auth;

import com.wayaapi.com.enums.Errors;
import com.wayaapi.com.exception.WayaExceptions;
import com.wayaapi.com.service.AccountService;
import com.wayaapi.com.util.Util;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.Flowable;
import lombok.SneakyThrows;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

import static java.util.Collections.singletonList;

@Singleton
public class RefreshTokenHandler implements RefreshTokenPersistence {

    @Inject
    AccountService accountService;

    @Inject
    Util util;

    @Override
    @EventListener
    public void persistToken(RefreshTokenGeneratedEvent event) {
        accountService.saveRefreshToken(event.getUserDetails().getUsername(), event.getRefreshToken());
    }

    @SneakyThrows
    @Override
    public Publisher<UserDetails> getUserDetails(String refreshToken) {
        return accountService.findByRefreshToken(refreshToken)
                .map(user -> Flowable.just(new UserDetails(user.getUsername(), singletonList(user.getUsername()))))
                .orElse(Flowable.error(new WayaExceptions(util.getError(Errors.TOKEN_NOT_FOUND.getErrorCode(), Errors.TOKEN_NOT_FOUND.getErrorMsg()))));

    }
}
