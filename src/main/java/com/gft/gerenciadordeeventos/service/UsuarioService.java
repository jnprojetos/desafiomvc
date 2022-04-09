package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Permissao;
import com.gft.gerenciadordeeventos.model.Usuario;
import com.gft.gerenciadordeeventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario buscarPorNome(String nome){
        return usuarioRepository.findByNome(nome).get();
    }
    public Usuario adicionar(Usuario usuario){
        var usuarioExistente = usuarioRepository.findByNome(usuario.getNome());
        if(usuarioExistente.isPresent()){
            throw new RuntimeException("Usuário já cadastrado.");
        }
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario.setPermissoes(Collections.singletonList(new Permissao(2L)));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(String nome){
        if (nome != null && nome != ""){
            return usuarioRepository.findByNomeContains(nome).get();
        }
        return usuarioRepository.findAll();
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não localizado."));
        usuarioRepository.delete(usuario);
    }
}
