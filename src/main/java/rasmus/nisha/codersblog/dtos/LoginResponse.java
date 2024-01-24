package rasmus.nisha.codersblog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import rasmus.nisha.codersblog.entites.Role;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class LoginResponse {
    private String username;
    private Role role;

    private String token;
}
