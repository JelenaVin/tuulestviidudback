package ee.valiit.tuulestviidudback.controller.login;

import ee.valiit.tuulestviidudback.persistance.user.UserRepository;
import ee.valiit.tuulestviidudback.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    @Operation(summary = "Kasutaja logib sisse. Süsteem tagastab userId ja roleName")

    public LoginResponse login (@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

}


