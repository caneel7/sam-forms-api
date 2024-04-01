package com.wancan.samformapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wancan.samformapi.dto.FormResponseConstants;
import com.wancan.samformapi.dto.ResponseDTO;
import com.wancan.samformapi.libs.Recaptcha;
import com.wancan.samformapi.model.FormModel;
import com.wancan.samformapi.model.FormResponseModel;
import com.wancan.samformapi.respository.FormRepository;
import com.wancan.samformapi.respository.FormResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class FormResponseService {

    @Autowired
    private Recaptcha recaptcha;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormResponseRepository formResponseRepository;

    public void submitForm(FormResponseConstants body) throws Exception{
//       boolean isValidRecaptcha = recaptcha.verifyRecaptch(body.getRecaptchaToken());
//       if(!isValidRecaptcha){
//           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Recaptcha");
//       }
        Optional<FormModel> foundForm = formRepository.findByIdAndIsActive(body.getFormId(), true);
       if(foundForm.isEmpty()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot Find Form");
       }
       ObjectMapper objectMapper = new ObjectMapper();
       formResponseRepository.save(FormResponseModel.builder()
               .id(UUID.randomUUID().toString())
               .formId(foundForm.get().getId())
               .data(objectMapper.writeValueAsString(body.getData()))
               .build()

       );
       return;
    }

    public FormResponseModel fetchFormResponse(String id) throws Exception{
        Optional<FormResponseModel> foundFormResponse = formResponseRepository.findById(id);
        if(foundFormResponse.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot Find Request Form Response");
        }
        return foundFormResponse.get();
    }

}
