package com.wancan.samformapi.controller;

import com.wancan.samformapi.dto.AuthenticationDTO;
import com.wancan.samformapi.dto.ResponseDTO;
import com.wancan.samformapi.dto.UserConstants;
import com.wancan.samformapi.model.UserModel;
import com.wancan.samformapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<ResponseDTO> userRegister(@RequestBody UserConstants.ResigterDTO body) throws Exception{
        try{
            UserModel user = userService.createNewUser(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.builder().success(true).message("User Created Successfully").build());
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserConstants.LoginDTO body)throws Exception{
        try{
            AuthenticationDTO response = userService.loginUser(body);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder().success(true).data(response).message("Logged In SuccessFully").build());
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }

}
