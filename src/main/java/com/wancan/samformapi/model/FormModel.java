package com.wancan.samformapi.model;

import com.wancan.samformapi.dto.FormDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "forms")
@Builder
public class FormModel {

    @Id
    private String id;
    private String userId;
    private String alias;
    private List<FormDTO> data;
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
