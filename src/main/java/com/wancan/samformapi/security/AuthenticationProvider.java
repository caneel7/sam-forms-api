package com.wancan.samformapi.security;

import com.wancan.samformapi.model.UserModel;
import com.wancan.samformapi.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    public UserModel getUser() throws Exception{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Auth"+ auth);
        if(auth == null || !(auth.getPrincipal() instanceof String)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Or Expired Token");
        }
        String id = auth.getPrincipal().toString();
        System.out.println("id"+id);
        if(id == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Or Expired Token");
        }

        Optional <UserModel> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Cannot Find User");
        }

        return foundUser.get();
    }

}
