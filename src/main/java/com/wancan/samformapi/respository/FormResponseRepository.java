package com.wancan.samformapi.respository;

import com.wancan.samformapi.model.FormResponseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface FormResponseRepository extends MongoRepository<FormResponseModel,String> {
}
