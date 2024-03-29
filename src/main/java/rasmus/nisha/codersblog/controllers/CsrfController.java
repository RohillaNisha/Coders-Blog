package rasmus.nisha.codersblog.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CsrfController {


        @GetMapping("/csrf")
        public CsrfToken csrfToken(CsrfToken csrfToken) {
            System.out.println("csrf token: " + csrfToken.getToken());
            return csrfToken;
        }
}
