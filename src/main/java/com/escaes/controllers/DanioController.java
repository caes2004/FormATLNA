package com.escaes.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.escaes.models.Danios;
import com.escaes.models.enums.Estado;
import com.escaes.models.enums.Sede;
import com.escaes.repositories.DanioRepository;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class DanioController {


    private DanioRepository dañoRepo;

    @Autowired
    private Cloudinary cloudinary;

    public DanioController(DanioRepository dañoRepo) {
        this.dañoRepo = dañoRepo;
    }


    @GetMapping("/")
    public String getAddDañosView(Model model) {

        model.addAttribute("danio", new Danios());
        model.addAttribute("sedes", Sede.values());
        return "formularioDanio";
    }
    
    @PostMapping("/")
    public String postAddDanios(@RequestParam("imagenFile")MultipartFile imagenFile,Danios danio)throws IOException{
        

        
        if (!imagenFile.isEmpty()) {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(imagenFile.getBytes(), ObjectUtils.emptyMap());

        // Guardar la URL de la imagen en lugar del nombre del archivo
            String imageUrl = uploadResult.get("secure_url").toString();

            // Solo almacenar el nombre de la imagen en la base de datos
            danio.setImagen(imageUrl);
        }

        danio.setFechaReporte(LocalDate.now());
        danio.setEstado(Estado.RECIBIDO);
        dañoRepo.save(danio);
        
        return "redirect:/?success";
    }
    

    

}
