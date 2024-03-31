package com.wancan.samformapi.dto;

import lombok.Data;

public class UserConstants {

    @Data
    public static class ResigterDTO{
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }

    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }
}
