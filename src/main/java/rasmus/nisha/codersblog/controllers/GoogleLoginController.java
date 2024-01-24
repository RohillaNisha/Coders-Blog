package rasmus.nisha.codersblog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import rasmus.nisha.codersblog.dtos.LoginResponse;
import rasmus.nisha.codersblog.entites.Role;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
public class GoogleLoginController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private UserRepository userRepository;
/*
    @GetMapping("/api/google/login")
    public ResponseEntity<String> googleLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
        String token = client.getAccessToken().getTokenValue();
        System.out.println(token);
        System.out.println(client.getAccessToken().getScopes());
        System.out.println("Authentication when login with Google " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return ResponseEntity.ok(token);
    }

 */


    @GetMapping("/api/google/login/callback")
    public ResponseEntity<LoginResponse> googleLogin(@AuthenticationPrincipal OAuth2User principal, HttpServletResponse response){
        String googleId = principal.getAttribute("sub");
        String email = principal.getAttribute("email");
        String username = principal.getAttribute("name");

        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty()){
            User newUser = new User();
            newUser.setGoogleId(googleId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setRole(Role.ROLE_USER);

            userRepository.save(newUser);
        }
/*
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600) // Same as JWT token expiry time.
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

 */

         LoginResponse loginResponse = LoginResponse.builder()
                .username(username)
                .role(Role.ROLE_USER)
                .build();

         return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/google/login")
    public ResponseEntity<String> initiateLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client){
        String token = client.getAccessToken().getTokenValue();
        System.out.println("Access token from server: " + token);

        return ResponseEntity.ok(token);
    }




/*
    @GetMapping("/login")
    public RedirectView googleLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
        String token = client.getAccessToken().getTokenValue();
        System.out.println(token);
        System.out.println(client.getAccessToken().getScopes());

        return new RedirectView("http://localhost:3000/logged-in-view");
    }

 */
/*
    @GetMapping("/google")
    public void startGoogleOAuth2Flow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClientRegistration googleRegistration = clientRegistrationRepository.findByRegistrationId("google");
        String redirectUri = "http://localhost:8080/api/auth/google/callback";

        OAuth2AuthorizationRequestRedirectFilter filter = new OAuth2AuthorizationRequestRedirectFilter();
        filter.setAuthorizationRequestBaseUri("")
    }

 */
}
