package com.wancan.samformapi.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "form_responses")
@Builder
public class FormResponseModel {

    @Id
    private String id;
    private String userId;
    private String formId;
    private String data;
    @Builder.Default
    private boolean isActive = true;
    @Builder.Default
    private boolean isDeleted = false;
    @CreatedDate
    @Builder.Default
    private Date createdAt = new Date();
    @LastModifiedDate
    @Builder.Default
    private Date updatedAt = new Date();
    private Date deletedAt;

}
