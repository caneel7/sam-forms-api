package com.wancan.samformapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class ResponseDTO {
    private boolean success;
    private String message;
    private Object data;
}
