package com.wancan.samformapi.respository;

import com.wancan.samformapi.model.FormModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FormRepository extends MongoRepository<FormModel,String> {

    Optional<FormModel> findByIdAndIsActive(String id, boolean isActive);

}
