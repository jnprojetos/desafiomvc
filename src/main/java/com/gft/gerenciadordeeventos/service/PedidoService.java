package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Pedido;
import com.gft.gerenciadordeeventos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Transactional
    public Pedido adicionar(Pedido pedido, Long idEvento){
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        var evento = eventoService.buscarPorId(idEvento);
        pedido.setUsuario(usuarioService.buscarPorNome(nomeUsuario));
        pedido.setEvento(evento);
        pedido.setDataPedido(LocalDate.now());
        evento.atualizaCapacidade(pedido.getQuantidade());
        pedido.calcularTotalPedido(pedido.getQuantidade(), pedido.getEvento().getPrecoIngresso());

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> historicoPedidos(String nomeUsuario){
        var usuario = usuarioService.buscarPorNome(nomeUsuario);
        return pedidoRepository.findAllPedidoByUser(usuario.getId());
    }
}
