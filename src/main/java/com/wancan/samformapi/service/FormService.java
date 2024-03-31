package com.wancan.samformapi.service;

import com.wancan.samformapi.dto.FormDTO;
import com.wancan.samformapi.model.FormModel;
import com.wancan.samformapi.model.UserModel;
import com.wancan.samformapi.respository.FormRepository;
import com.wancan.samformapi.security.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    public FormModel addNewForm(FormModel body) throws Exception{
        try{
            UserModel user = authenticationProvider.getUser();

            FormModel newForm = FormModel.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(user.getId()).alias(body.getAlias())
                    .data(body.getData())
                    .build();
            formRepository.save(newForm);
            return newForm;

        }catch (Exception err){
            throw err;
        }
    }

    public List<FormDTO> getForm(String id){
        Optional<FormModel> form = formRepository.findByIdAndIsActive(id,true);
        if(form.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cannot Find Form");
        }
        return form.get().getData();
    }

}
