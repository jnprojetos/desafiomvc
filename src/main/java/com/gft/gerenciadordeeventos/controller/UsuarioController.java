package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.Usuario;
import com.gft.gerenciadordeeventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("novo")
    public ModelAndView novoUsuario(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuario/form-usuario");
        return mv;
    }

    @PostMapping("novo")
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoUsuario(usuario);
        }
        try {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            usuarioService.adicionar(usuario);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
           // bindingResult.rejectValue("senha", e.getMessage(), e.getMessage());

            return novoUsuario(usuario);
        }

        attributes.addFlashAttribute("message", "Usuario Salvo com sucesso.");

        return new ModelAndView("redirect:/usuarios/novo");
    }

    @GetMapping
    public ModelAndView listarUsuarios(String nome){
        ModelAndView mv = new ModelAndView("usuario/listar-usuario");
        mv.addObject("lista", usuarioService.listarUsuarios(nome));
        return mv;
    }
}
