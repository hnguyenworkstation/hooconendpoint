package com.greencode.hooconservice.Services;

import com.greencode.hooconservice.Constants.DBConstant;
import com.greencode.hooconservice.Database.MLabDatabase;
import com.greencode.hooconservice.Modules.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Repository
public class UserService {

    @PostConstruct
    public void init() {

    }

    public Object create(User user) throws Exception {
        MongoOperations mongoOperations = MLabDatabase.getMongoOperations();

        if (mongoOperations != null) {
            if (mongoOperations.getCollection(DBConstant.USER_COLLECTION) == null) {
                mongoOperations.createCollection(DBConstant.USER_COLLECTION);
            } else {
                mongoOperations.insert(user,DBConstant.USER_COLLECTION);
            }
        }

        return user;
    }
}
