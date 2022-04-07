package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "A capcidade do evento é obrigatória.")
    private Integer capacidade;

    @NotNull(message = "O preço do ingresso é obrigatório.")
    @Digits(fraction = 2, integer = 10)
    private BigDecimal precoIngresso;

    @NotNull(message = "A data do evento é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data não pode ser no passado.")
    private LocalDate dataEvento;

    @NotBlank
    private String horario;

    @NotBlank(message = "A url da imagem é obrigatória.")
    private String urlImagem;

    @NotNull(message = "Selecione uma casa de show")
    @ManyToOne
    private CasaDeShow casaDeShow;

    @NotNull(message = "Selecione gênero musical")
    @ManyToOne
    private GeneroMusical generoMusical;

    public void atualizaCapacidade(Integer qtde){
        this.capacidade = this.capacidade - qtde;
    }
}
