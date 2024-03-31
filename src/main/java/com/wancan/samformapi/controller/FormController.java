package com.wancan.samformapi.controller;

import com.wancan.samformapi.dto.FormDTO;
import com.wancan.samformapi.dto.ResponseDTO;
import com.wancan.samformapi.model.FormModel;
import com.wancan.samformapi.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping("/form")
    public ResponseEntity<ResponseDTO> createNewForm(@RequestBody FormModel body) throws Exception {
        System.out.println("creating new form");
        try{
            formService.addNewForm(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.builder().success(true).message("Form Added").build());
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }

    @GetMapping("/form/{id}")
    public ResponseEntity<ResponseDTO> fetchForm(@PathVariable String id) throws Exception{
        try{
            List<FormDTO> formData =  formService.getForm(id);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder().success(true).message("Form Fetched").data(formData).build()) ;
        }catch (ResponseStatusException err){
            return ResponseEntity.status(err.getStatusCode()).body(ResponseDTO.builder().success(false).message(err.getReason()).build());
        }
    }
}
