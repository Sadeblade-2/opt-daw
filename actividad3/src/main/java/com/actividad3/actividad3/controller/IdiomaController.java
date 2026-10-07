package com.actividad3.actividad3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IdiomaController {

    @GetMapping("/elegir")
    public String elegirIdioma(@RequestParam(name = "idioma", required = false) String idioma) {

        if (idioma == null) {
            return "redirect:/english.html";
        }

        return switch (idioma) {
            case "spanish" -> "redirect:/spanish.html";
            case "french" -> "redirect:/french.html";
            case "english" -> "redirect:/english.html";
            case "german" -> "redirect:/german.html";
            default -> "redirect:/english.html";
        };
    }
}