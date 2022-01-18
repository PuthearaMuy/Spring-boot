package com.springboot.theara.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=repository.findByUserName(username);
        if(user.equals(null))
            throw new UsernameNotFoundException("No this user account.");

        return new UserDetail(user.getUserName(),user.getPassword(),user.getAuthentication());
    }
}
