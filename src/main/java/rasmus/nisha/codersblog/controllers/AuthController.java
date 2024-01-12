package rasmus.nisha.codersblog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rasmus.nisha.codersblog.dtos.LoginDto;
import rasmus.nisha.codersblog.services.AuthenticationService;
import rasmus.nisha.codersblog.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String login = authenticationService.login(loginDto);
        return ResponseEntity.ok(login);
    }
}
