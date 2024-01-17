package rasmus.nisha.codersblog.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/google")
public class GoogleLoginController {

    @GetMapping("/login")
    public void googleLogin(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
        String token = client.getAccessToken().getTokenValue();
        System.out.println(token);
        System.out.println(client.getAccessToken().getScopes());
    }
}
