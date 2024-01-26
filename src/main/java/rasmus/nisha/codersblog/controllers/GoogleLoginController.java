package rasmus.nisha.codersblog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GoogleLoginController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private UserRepository userRepository;

/*    @GetMapping("api/google/login")
    public ResponseEntity<String> initiateLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client){
        String token = client.getAccessToken().getTokenValue();
        System.out.println("Access token from server: " + token);

        return ResponseEntity.ok(token);
    }*/

    @GetMapping("api/google/login")
    public ResponseEntity<String> initiateLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client){
        System.out.println("I AM IN GOOGLE CONTROLLER");
        String token = client.getAccessToken().getTokenValue();
        System.out.println("Access token from server: " + token);
        return ResponseEntity.ok("token");
    }


/*


    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<LoginResponse> googleLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client , @AuthenticationPrincipal OAuth2User principal, HttpServletResponse response){
        String googleId = principal.getAttribute("sub");
        String email = principal.getAttribute("email");
        String username = principal.getAttribute("name");

        System.out.println("GoogleId"+ googleId);
        System.out.println("Email"+ email);


        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty()){
            User newUser = new User();
            newUser.setGoogleId(googleId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setRole(Role.ROLE_USER);

            userRepository.save(newUser);
        }

        LoginResponse loginResponse = LoginResponse.builder()
                .username(username)
                .role(Role.ROLE_USER)
                .build();

        System.out.println("Login response from builder " + loginResponse);

        return ResponseEntity.ok(loginResponse);
    }
*/


}