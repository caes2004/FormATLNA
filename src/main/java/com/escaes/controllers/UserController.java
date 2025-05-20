package com.escaes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.escaes.models.Danios;
import com.escaes.repositories.DanioRepository;
import com.escaes.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository userRepository;

    private DanioRepository danioRepository;

    public UserController(UserRepository userRepository, DanioRepository danioRepository) {
        this.userRepository = userRepository;
        this.danioRepository = danioRepository;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/reportes")
    public String getReportesView(Model model) {

        model.addAttribute("danios", danioRepository.findAll());

        return "reportes";
    }

    @GetMapping("/danio/{id}")
    public String getMethodName(@PathVariable("id") Long id, Model model) {
        Danios danio = danioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Daño no encontrado con ID: " + id));

        model.addAttribute("danio", danio);       
        return "detalle";
    }

}
