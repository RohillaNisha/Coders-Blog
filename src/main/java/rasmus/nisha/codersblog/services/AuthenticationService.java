package rasmus.nisha.codersblog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import rasmus.nisha.codersblog.dtos.LoginDto;
import rasmus.nisha.codersblog.dtos.LoginResponse;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public LoginResponse login(LoginDto loginDto){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        String token = jwtService.generateToken(user);
        LoginResponse response = LoginResponse.builder()
                        .username(user.getUsername())
                                .role(user.getRole())
                                        .token(token)
                                                .build();
        System.out.println("token on login " + token);
        return response;
    }
}
