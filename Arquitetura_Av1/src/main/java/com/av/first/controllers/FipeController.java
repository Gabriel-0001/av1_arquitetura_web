package com.av.first.controllers;

import com.av.first.services.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FipeController {

    @Autowired
    private FipeService fipeService;

    @GetMapping("/consultarMarcas")
    public String consultarMarcas() {

        return fipeService.consultarMarcas();
    }

    @GetMapping("/consultarModelos/{marca}")
    public String consultarModelos(@PathVariable int marca) {

        return fipeService.consultarModelos(marca);
    }

    @GetMapping("/consultarAnos/{marca}/{modelo}")
    public String consultarAnos(@PathVariable int marca, @PathVariable int modelo) {

        return fipeService.consultarAnos(marca, modelo);
    }

    @GetMapping("/consultarValor/{marca}/{modelo}/{ano}")
    public String consultarValor(@PathVariable int marca, @PathVariable int modelo, @PathVariable String ano) {

        return fipeService.consultarValor(marca, modelo, ano);
    }
}
