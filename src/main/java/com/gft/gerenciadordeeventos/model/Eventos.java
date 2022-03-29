package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "Informa a capacidade do evento.")
    private Integer capacidade;

    @NotNull(message = "Informa o valor do ingresso.")
    private BigDecimal precoIngresso;

    @NotNull(message = "Informe a data da realização do evento")
    private LocalDate dataEvento;

    @NotNull(message = "Selecione uma casa de show")
    @ManyToOne
    private CasaDeShow casaDeShow;

    @NotNull(message = "Selecione gênero musical")
    @ManyToOne
    private GeneroMusical generoMusical;
}
