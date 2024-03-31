package com.wancan.samformapi.service;

import com.wancan.samformapi.dto.AuthenticationDTO;
import com.wancan.samformapi.dto.UserConstants;
import com.wancan.samformapi.model.UserModel;
import com.wancan.samformapi.respository.UserRepository;
import com.wancan.samformapi.security.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWT jwt;

    public UserModel createNewUser(UserConstants.ResigterDTO body) throws Exception{
        Optional<UserModel> foundUser = userRepository.findByEmailAndIsActive(body.getEmail(), true);
        if(foundUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Already Exists");
        }
        String hashPassword = passwordEncoder.encode(body.getPassword());
        UserModel newUser = UserModel.builder()
                .id(UUID.randomUUID().toString())
                .email(body.getEmail())
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .password(hashPassword).build();
        userRepository.save(newUser);
        return newUser;
    }

    public AuthenticationDTO loginUser(UserConstants.LoginDTO body){
        Optional<UserModel> foundUser = userRepository.findByEmailAndIsActive(body.getEmail(), true);
        if(foundUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Cannot Find User");
        }
        boolean match = passwordEncoder.matches(body.getPassword(),foundUser.get().getPassword());
        if(!match){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Credentials");
        }
        String token = jwt.generateToken(foundUser.get());
        return AuthenticationDTO.builder().user(foundUser.get()).token(token).build();
    }

}
