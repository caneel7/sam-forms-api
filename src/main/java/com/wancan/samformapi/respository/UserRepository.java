package com.wancan.samformapi.respository;

import com.wancan.samformapi.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel,String> {

    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findById(String id);
}
