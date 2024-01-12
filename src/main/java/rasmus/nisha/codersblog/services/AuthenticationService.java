package rasmus.nisha.codersblog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import rasmus.nisha.codersblog.dtos.LoginDto;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public String login(LoginDto loginDto){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        return jwtService.generateToken(user);
    }
}
