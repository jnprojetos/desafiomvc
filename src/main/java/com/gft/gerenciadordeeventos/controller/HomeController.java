package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("home");
        String nomeUsuarioLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        mv.addObject("usuario", nomeUsuarioLogado);
        mv.addObject("lista", eventoService.listarTodosEventos());
        return mv;
    }
}
