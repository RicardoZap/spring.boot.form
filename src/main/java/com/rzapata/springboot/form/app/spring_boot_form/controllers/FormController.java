package com.rzapata.springboot.form.app.spring_boot_form.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import com.rzapata.springboot.form.app.spring_boot_form.models.domain.Usuario;
import com.rzapata.springboot.form.app.spring_boot_form.validation.UsuarioValidador;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @RequestMapping({"/", ""})
    public String redirectForm() {
        return "redirect:/form";
    }

    /* @Autowired
    private UsuarioValidador validador;

    @InitBinder
    public void InitBinder(WebDataBinder binder){
        binder.setValidator(validador);
    } */

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("John"); 
        usuario.setApellido("Doe");
        usuario.setIdentificador("123.456.789-K");
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
        //validador.validate(usuario, result);
        model.addAttribute("titulo", "Resultado Form");
        if (result.hasErrors()) {
            /* Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores); */
            return "form";
        }
        model.addAttribute("usuario", usuario);
        status.setComplete();
        return "resultado";
    }
    
}
