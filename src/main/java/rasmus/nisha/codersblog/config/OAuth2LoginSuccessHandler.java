package rasmus.nisha.codersblog.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rasmus.nisha.codersblog.dtos.LoginResponse;
import rasmus.nisha.codersblog.entites.Role;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;
import rasmus.nisha.codersblog.services.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public OAuth2LoginSuccessHandler(UserService userService, RestTemplate restTemplate, UserRepository userRepository) {
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String redirectUrl = null;
/*

        if(authentication instanceof OAuth2AuthenticationToken oauthToken ){
            OAuth2User oauth2User = oauthToken.getPrincipal();

            Map<String, Object> attributes = oauth2User.getAttributes();
            String accessToken = attributes.get("access_token").toString();

            request.getSession().setAttribute("accessToken", accessToken);


        }
*/

        if(authentication.getPrincipal() instanceof DefaultOAuth2User userDetails) {
            String email = userDetails.getAttribute("email");
            String googleId = userDetails.getAttribute("sub");
            String username = userDetails.getAttribute("name");

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
            String storedAccessToken = (String) request.getSession().getAttribute("accessToken");
*/


            redirectUrl ="http://localhost:3000/logged-in-view" +
                    "?username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                    "&role=ROLE_USER" ;
            // Optionally, you can also set an authentication token in the response headers
        //    response.setHeader("Authorization", "Bearer " + storedAccessToken);  // Replace with your actual token generation logic

            }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);


    }


}