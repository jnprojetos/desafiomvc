package com.gft.gerenciadordeeventos.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Entity
@Data
public class Permissao implements GrantedAuthority {

    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
