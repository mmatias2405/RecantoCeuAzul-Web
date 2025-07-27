package com.recantoceuazul.web.controller;

import com.recantoceuazul.web.model.Administrador;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final String API_URL = "http://localhost:8081/api/administrador";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String index(Model model) {
        ResponseEntity<Administrador[]> response = restTemplate.getForEntity(API_URL, Administrador[].class);
        List<Administrador> administradores = Arrays.asList(response.getBody());
        model.addAttribute("administradores", administradores);
        model.addAttribute("administrador", new Administrador());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/consumo")
    public String consumo(Model model) {
        return "registrarConsumo";
    }

    @GetMapping("/captacao")
    public String captacao(Model model) {
        return "registrarCaptacao";
    }

    @GetMapping("/setor")
    public String setor(Model model) {
        return "registrarSetor";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Administrador administrador) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Administrador> request = new HttpEntity<>(administrador, headers);
        restTemplate.postForEntity(API_URL, request, Administrador.class);
        return "redirect:/";
    }
}
