package integration4.evalebike.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
    public CustomUserDetails(final String username, final String password) {
        super(username, password, Collections.emptyList());
    }
}


