package com.homework.Dev_SpringBoot_HW.service;

import com.homework.Dev_SpringBoot_HW.data.entities.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  Optional.ofNullable(userService.findById(username))
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username "+username+" not found"));
    }
}
