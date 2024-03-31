package com.wancan.samformapi.config;

import com.wancan.samformapi.model.UserModel;
import com.wancan.samformapi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository repo){
        this.userRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Cannot Find User"));
        return new User(user.getFirstName(),user.getPassword(),null);
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException{
        UserModel user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Cannot Find User"));
        return new User(user.getId(), user.getId(), null);
    }
}
