package rasmus.nisha.codersblog.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rasmus.nisha.codersblog.entites.Role;
import rasmus.nisha.codersblog.entites.User;
import rasmus.nisha.codersblog.repositories.UserRepository;

@Component
public class InbuiltUsers {

    private final UserRepository userRepository;


    public InbuiltUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        var existing = this.userRepository.findAll();

        if(existing.isEmpty()){
            this.userRepository.save(new User(1, "Nisha", passwordEncoder.encode("nisha"), Role.ROLE_USER));
            this.userRepository.save(new User(2, "Rasmus", passwordEncoder.encode("rasmus"), Role.ROLE_ADMIN));
        }
    }
}
