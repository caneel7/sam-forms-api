package com.wancan.samformapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormResponseConstants {


    private String formId;
    private List<Object> data;
    private String recaptchaToken;
}