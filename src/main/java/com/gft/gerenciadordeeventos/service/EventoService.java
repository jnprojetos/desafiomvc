package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Evento;
import com.gft.gerenciadordeeventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public Evento adicionar(Evento evento){
        var eventoExistente = eventoRepository.findByNomeAndDataEvento(evento.getNome(), evento.getDataEvento());
        if (eventoExistente.isPresent()){
            throw new RuntimeException("Evento já cadastrado nessa data.");
        }
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos(String nome){
        if(nome != null && nome != ""){
            return listarEventoPorNome(nome);
        }
        else{
            return listarTodosEventos();
        }
    }
    private List<Evento> listarEventoPorNome(String nome){
        Optional<List<Evento>> lista = eventoRepository.findByNomeContains(nome);
        return lista.get();
    }

    public List<Evento> listarTodosEventos(){
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id){
       Evento evento = eventoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Evento não localzado."));
        return evento;
    }

    public void deletar(Long id){
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()){
            eventoRepository.delete(evento.get());
        }
        new Exception("Ocorreu um problema na exclusão");
    }
}
