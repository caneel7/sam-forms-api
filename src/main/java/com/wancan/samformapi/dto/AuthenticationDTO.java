package com.wancan.samformapi.dto;

import com.wancan.samformapi.model.UserModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDTO {
    private UserModel user;
    private String token;
}
