package com.wancan.samformapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wancan.samformapi.dto.FormDTO;
import com.wancan.samformapi.dto.FormResponseConstants;
import com.wancan.samformapi.dto.ResponseDTO;
import com.wancan.samformapi.dto.UserConstants;
import com.wancan.samformapi.libs.Recaptcha;
import com.wancan.samformapi.model.FormResponseModel;
import com.wancan.samformapi.service.FormResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FormResponseController {

    @Autowired
    private FormResponseService formResponseService;

    @PostMapping("/form/submit")
    public ResponseEntity<ResponseDTO> submitForm(@RequestBody FormResponseConstants body) throws Exception{
        try{
            formResponseService.submitForm(body);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder().success(true).message("Form Submitted").build());
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }

    @GetMapping("/form/response/{id}")
    public ResponseEntity<ResponseDTO> fetchFormResponse(@PathVariable String id) throws Exception{
        try{
            FormResponseModel formResponseModel = formResponseService.fetchFormResponse(id);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(formResponseModel.getData().getClass());
            List<FormResponseDataDTO> response = objectMapper.readValue(formResponseModel.getData(),new TypeReference<List<FormResponseDataDTO>>() {});
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder().success(true).data(response).message("Response Fetched").build());
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }

}
