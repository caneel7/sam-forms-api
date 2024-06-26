package com.wancan.samformapi.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
@Builder
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
    @Builder.Default
    private boolean isActive = true;
    @Builder.Default
    private boolean isDeleted = false;
    @CreatedDate
    @Builder.Default
    private Date createAt = new Date();
    @LastModifiedDate
    @Builder.Default
    private Date updatedAt = new Date();
    private Date deletedAt;
}
