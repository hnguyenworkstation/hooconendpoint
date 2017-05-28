package com.greencode.hooconservice.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Validation;
import javax.validation.Validator;


public class AuthenticateController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
}
