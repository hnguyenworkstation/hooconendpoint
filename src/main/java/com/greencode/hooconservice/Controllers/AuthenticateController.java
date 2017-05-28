package com.greencode.hooconservice.Controllers;

import com.google.gson.Gson;
import com.greencode.hooconservice.Modules.User;
import com.greencode.hooconservice.Services.UserService;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthenticateController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService = new UserService();
    }

    Gson gson = new Gson();

    @Path("/register")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(User user) {
        Map<Object, Object> apiResponse = new HashMap<>();
        Map<Object, Object> serviceResponse = new HashMap<Object, Object>();

        try {
            Set<ConstraintViolation<User>> validateErrors = validator.validate(user);
            //logger.info("creating new user email=" + user.getEmail());
            if (validateErrors.isEmpty()) {
                logger.debug("Calling user service: " + user.getPhoneNumber());
                user = (User) userService.create(user);
                logger.debug("Done calling userservice");
                if (user != null) {
                    logger.debug("User done creating user with email :" + user.getPhoneNumber());
                    //serviceResponse.put("created", user);
                    apiResponse.put("apiresponse", user);
                    return Response.ok(apiResponse).build();
                }
            } else {
                for (ConstraintViolation<User> error : validateErrors) {
                    apiResponse.put(error.getPropertyPath().toString(),
                            error.getMessage());
                }
                return Response.status(400).entity(apiResponse).build();
            }
        } catch (DuplicateKeyException e){
            logger.error("Error occured creating user:",e);
            apiResponse.put("error", "Duplicate user found for emaill address:" +user.getPhoneNumber());
        } catch (Exception e) {
            logger.error("Error occured creating user:", e);
            apiResponse.put("error", e.getMessage());
        }

        return Response.status(500).entity(apiResponse).build();
    }
}
