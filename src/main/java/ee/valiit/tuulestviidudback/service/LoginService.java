package ee.valiit.tuulestviidudback.service;


import ee.valiit.tuulestviidudback.controller.login.LoginResponse;
import ee.valiit.tuulestviidudback.infrastructure.exception.ForbiddenException;
import ee.valiit.tuulestviidudback.persistance.user.User;
import ee.valiit.tuulestviidudback.persistance.user.UserMapper;
import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public LoginResponse login(String username, String password) {
        User user = userRepository.findUserBy(username, password)
                .orElseThrow(() -> new ForbiddenException("Vale kasutajanimi või parool",111));
        return userMapper.toLoginResponse(user);
    }
}
