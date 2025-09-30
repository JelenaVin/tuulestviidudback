package ee.valiit.tuulestviidudback.service;


import ee.valiit.tuulestviidudback.Status;
import ee.valiit.tuulestviidudback.controller.login.LoginResponse;
import ee.valiit.tuulestviidudback.infrastructure.exception.ForbiddenException;
import ee.valiit.tuulestviidudback.persistance.subscription.SubscriptionRepository;
import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.user.UserMapper;
import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public LoginResponse login(String username, String password) {
        User user = userRepository.findUserBy(username, password, Status.ACTIVE.getCode())
                .orElseThrow(() -> new ForbiddenException("Vale kasutajanimi või parool",111));
        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        loginResponse.setHasSubscription(subscriptionRepository.subscriptionExistsBy(user, LocalDate.now()));
        return loginResponse;
    }
}
