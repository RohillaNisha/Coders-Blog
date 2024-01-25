package rasmus.nisha.codersblog.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rasmus.nisha.codersblog.dtos.LoginResponse;
import rasmus.nisha.codersblog.services.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if(isGoogleRedirect(request)){
            super.onAuthenticationSuccess(request, response, authentication);
        }
        else{
            ResponseEntity<LoginResponse> callbackResponse = restTemplate.getForEntity("http://localhost:8080/login/oauth2/code/google", LoginResponse.class);
            LoginResponse loginResponse = callbackResponse.getBody();
            System.out.println("Here " + callbackResponse);
        }
        // response.sendRedirect("http://localhost:3000/blogs");

    }

    private boolean isGoogleRedirect(HttpServletRequest request){
        return "api/google/login".equals(request.getServletPath());
    }

}