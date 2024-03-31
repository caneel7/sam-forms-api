package com.wancan.samformapi.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
public class UserModel {

    @Id

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String hashPassword;
    private String lastOtp;
    private boolean isActive;
    private boolean isDeleted;
    private Date createAt;
    private Date updatedAt;
    private Date deletedAt;
}
