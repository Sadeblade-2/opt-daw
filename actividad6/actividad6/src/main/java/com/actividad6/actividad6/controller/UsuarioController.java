package com.actividad6.actividad6.controller;



import com.actividad6.actividad6.model.UsuarioRegistro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UsuarioController {

    private final Map<String, String> usuarios = new HashMap<>();

    public UsuarioController() {
        usuarios.put("ana@example.com", "246810");
        usuarios.put("luis@example.com", "clave123");
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioRegistro());
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public String registrarUsuario(@ModelAttribute UsuarioRegistro usuario) {

        String nombre = valorSeguro(usuario.getNombre());
        String apellidos = valorSeguro(usuario.getApellidos());
        String correo = valorSeguro(usuario.getCorreo());
        String contrasena = valorSeguro(usuario.getContrasena());

        if (nombre.isEmpty() || correo.isEmpty()) {
            return """
                    <h1>Error en el registro</h1>
                    <p>El nombre y el correo electrónico son obligatorios.</p>
                    <a href="/registro">Volver al registro</a>
                    """;
        }

        boolean contrasenaGenerada = contrasena.isEmpty();

        if (contrasenaGenerada) {
            contrasena = generarContrasena();
        }

        usuarios.put(correo, contrasena);

        String nombreCompleto = apellidos.isEmpty()
                ? nombre
                : nombre + " " + apellidos;

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("<h1>Usuario registrado</h1>");
        mensaje.append("<p>Nombre completo: ").append(nombreCompleto).append("</p>");
        mensaje.append("<p>Correo: ").append(correo).append("</p>");

        if (contrasenaGenerada) {
            mensaje.append("<p>Contraseña generada: ").append(contrasena).append("</p>");
        }

        mensaje.append("<a href=\"/login\">Ir al login</a>");

        return mensaje.toString();
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String iniciarSesion(
            @RequestParam String usuario,
            @RequestParam String contrasena) {

        String correo = valorSeguro(usuario);
        String password = valorSeguro(contrasena);

        if (!usuarios.containsKey(correo)) {
            return """
                    <h1>Error</h1>
                    <p>Usuario inexistente.</p>
                    <a href="/login">Volver al login</a>
                    """;
        }

        if (!usuarios.get(correo).equals(password)) {
            return """
                    <h1>Error</h1>
                    <p>Contraseña incorrecta.</p>
                    <a href="/login">Volver al login</a>
                    """;
        }

        return """
                <h1>Inicio de sesión correcto.</h1>
                <p>Bienvenido, %s.</p>
                """.formatted(correo);
    }

    private String valorSeguro(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private String generarContrasena() {
        Random random = new Random();
        int numero = random.nextInt(1000000);
        return String.format("%06d", numero);
    }
}