package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.config.SecurityConfig;
import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.NotActiveException;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            if(user.getStatus() != UserStatus.ACTIVE){
                throw new UsernameNotFoundException("User status is not Active.");
            }
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(user.getPassword());
            builder.roles(user.getRoles().parallelStream()
                    .map(role -> role.toString())
                    .collect(Collectors.toList())
                    .toArray(new String[0]));	// convert to list of strings and then to array
        } else {
            throw new UsernameNotFoundException("User Not Found.");
        }
        return builder.build();
    }
}
