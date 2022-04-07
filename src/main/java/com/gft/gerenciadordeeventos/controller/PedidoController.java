package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.Pedido;
import com.gft.gerenciadordeeventos.service.EventoService;
import com.gft.gerenciadordeeventos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EventoService eventoService;

    @RequestMapping(method = RequestMethod.GET, path = "novo-pedido")
    public ModelAndView novoPedido(Pedido pedido, @RequestParam Long id){
        ModelAndView mv = new ModelAndView("pedido/form-pedido");
        mv.addObject("evento", eventoService.buscarPorId(id));
        mv.addObject("pedido", new Pedido());
        return mv;
    }

    @GetMapping("historico")
    public ModelAndView historico(){
        ModelAndView mv = new ModelAndView("pedido/historico-pedido");
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        mv.addObject("pedidos", pedidoService.historicoPedidos(nomeUsuario));
        mv.addObject("usuario", nomeUsuario);
        return mv;
    }

    @PostMapping("novo-pedido/{id_evento}")
    public ModelAndView salvarPedido(@Valid Pedido pedido, @PathVariable("id_evento") Long idEvento, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoPedido(pedido, null);
        }
        try {
            pedidoService.adicionar(pedido,idEvento);
        }catch (RuntimeException e){
            e.printStackTrace();
            bindingResult.rejectValue("quantidade", e.getMessage(), e.getMessage());
            return novoPedido(pedido, null);
        }

        attributes.addFlashAttribute("message", "Pedido finalizado com sucesso.");

        return new ModelAndView("redirect:/pedidos/historico");
    }


}
