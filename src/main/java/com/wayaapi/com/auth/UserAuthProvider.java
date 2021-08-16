package com.wayaapi.com.auth;

import com.wayaapi.com.domain.dtos.UserDto;
import com.wayaapi.com.service.AccountService;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Optional;

@Singleton
public class UserAuthProvider implements AuthenticationProvider {

    @Inject
    AccountService accountService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {

        final String username = authenticationRequest.getIdentity().toString();
        final String password = authenticationRequest.getSecret().toString();

        Optional<UserDto> existingUser = accountService.findUser(username);

        return Flowable.just(

                existingUser.map(user -> {
                    if (user.getPassword().equals(password)) {
                        return new UserDetails((String) authenticationRequest.getIdentity(), Collections.singletonList(user.getFirstName()));
                    }
                    return new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
                })
                        .orElse(new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND))
        );

    }
}
