package com.greencode.hooconservice;

import com.greencode.hooconservice.Controllers.AuthenticateController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import javax.ws.rs.ApplicationPath;

@Configuration
@Controller
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(MultiPartFeature.class);

        //Controllers
        register(AuthenticateController.class);
    }
}