package com.escaes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.escaes.models.Users;
import com.escaes.models.enums.Sede;
import com.escaes.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    
    public DataInitializer(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }



    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
      userInit();
    }

    public void userInit(){
        String correo = "admin@escaes.com";

        if(userRepository.findByCorreoUsuario(correo).isEmpty()){

            
            Sede sede=Sede.ITAGUI;
            Users usuario= new Users();

            usuario.setCorreoUsuario(correo);
            usuario.setNombreUsuario("Admin");
            usuario.setSede(sede);
            usuario.setPassword(passwordEncoder.encode("123"));

            userRepository.save(usuario);

            System.out.println("Usuario guardado en la BD");



        }
    }

}
