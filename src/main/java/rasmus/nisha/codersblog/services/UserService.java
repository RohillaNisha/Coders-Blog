package rasmus.nisha.codersblog.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rasmus.nisha.codersblog.entites.Role;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        var existing = this.userRepository.findAll();

        if(existing.isEmpty()){
            this.userRepository.save(new User(1, "Nisha", passwordEncoder.encode("nisha"), Role.ROLE_USER));
            this.userRepository.save(new User(2, "Rasmus", passwordEncoder.encode("rasmus"), Role.ROLE_ADMIN));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
