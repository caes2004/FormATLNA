package com.escaes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {

    @Bean
     public Cloudinary cloudinary() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing()
                .directory(System.getProperty("user.dir"))
                .filename(".env")
                .load();
                System.out.println("API KEY = " + dotenv.get("CLOUDINARY_API_KEY")); // nunca debe ser null


        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"),
                "api_key", dotenv.get("CLOUDINARY_API_KEY"),
                "api_secret", dotenv.get("CLOUDINARY_API_SECRET")
        ));
        
    }

}
