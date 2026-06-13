package com.Raul.AgendadorDeTarefas.business.dto;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnderecoDTO {

    private Long id;
    private String rua;

    private Long numero;

    private String cidade;

    private String estado;

    private String complemento;
    private String cep;
}
