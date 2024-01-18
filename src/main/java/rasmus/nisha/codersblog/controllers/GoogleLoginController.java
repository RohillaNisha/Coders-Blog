package rasmus.nisha.codersblog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/google")
public class GoogleLoginController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/login")
    public ResponseEntity<String> googleLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
        String token = client.getAccessToken().getTokenValue();
        System.out.println(token);
        System.out.println(client.getAccessToken().getScopes());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/googlelogin")
    public String getLoginPage(){
        return "login";
    }
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
