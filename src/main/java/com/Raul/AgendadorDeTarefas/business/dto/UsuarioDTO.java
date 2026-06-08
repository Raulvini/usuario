package com.Raul.AgendadorDeTarefas.business.dto;

import com.Raul.AgendadorDeTarefas.infrastructure.entity.Endereco;
import com.Raul.AgendadorDeTarefas.infrastructure.entity.Telefone;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
}
