package com.enterprise.ent_invest_backend.Jwt;

import com.entsite.ent_invest_backend.entity.User;
import com.entsite.ent_invest_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserEmail(userEmail);

        if (user.isPresent()) {
            User userInfo = user.get();

            // Assign user role to authorities
            List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_" + userInfo.getUserRole().trim())
            );

            return new org.springframework.security.core.userdetails.User(
                    userInfo.getUserEmail(),
                    userInfo.getPassword(),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("User with email " + userEmail + " not found!");
        }
    }
}

