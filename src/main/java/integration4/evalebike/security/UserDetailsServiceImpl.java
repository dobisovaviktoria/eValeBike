package integration4.evalebike.security;

import integration4.evalebike.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(developer -> {
                    return new CustomUserDetails(developer.getEmail(), developer.getPassword());
                })
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


}

