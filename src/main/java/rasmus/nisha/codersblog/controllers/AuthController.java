package rasmus.nisha.codersblog.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rasmus.nisha.codersblog.dtos.LoginDto;
import rasmus.nisha.codersblog.dtos.LoginResponse;
import rasmus.nisha.codersblog.services.AuthenticationService;
import rasmus.nisha.codersblog.services.UserService;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        LoginResponse loginRes = authenticationService.login(loginDto);

        ResponseCookie cookie = ResponseCookie.from("authToken", loginRes.getToken())
        .httpOnly(true)
                .secure(false)
                        .path("/")
                .maxAge(3600) // Same as JWT token expiry time.
                        .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(loginRes);
    }

    @GetMapping("/check-authentication")
    public boolean checkAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            return true;
        } return false;
    }
}
