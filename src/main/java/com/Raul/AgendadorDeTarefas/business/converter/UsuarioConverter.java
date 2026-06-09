package com.Raul.AgendadorDeTarefas.business.converter;


import com.Raul.AgendadorDeTarefas.business.dto.EnderecoDTO;
import com.Raul.AgendadorDeTarefas.business.dto.TelefoneDTO;
import com.Raul.AgendadorDeTarefas.business.dto.UsuarioDTO;
import com.Raul.AgendadorDeTarefas.infrastructure.entity.Endereco;
import com.Raul.AgendadorDeTarefas.infrastructure.entity.Telefone;
import com.Raul.AgendadorDeTarefas.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {



    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder().
                nome(usuarioDTO.getNome()).
                senha(usuarioDTO.getSenha()).
                email(usuarioDTO.getEmail()).
                enderecos(toEnderecos(usuarioDTO.getEnderecos())).
                telefones(toTelefones(usuarioDTO.getTelefones())).
        build();
    }

//    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
//
//    }



    public List<Endereco> toEnderecos(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream().map(this::toEndereco).toList();
    }

    public List<Telefone> toTelefones(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream().map(this::toTelefone).toList();
    }

    private Telefone toTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .rua(enderecoDTO.getRua())
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder().
                nome(usuario.getNome()).
                senha(usuario.getSenha()).
                email(usuario.getEmail()).
                enderecos(toEnderecosDTO(usuario.getEnderecos())).
                telefones(toTelefonesDTO(usuario.getTelefones())).
                build();
    }


    public List<EnderecoDTO> toEnderecosDTO(List<Endereco> endereco) {
        return endereco.stream().map(this::toEnderecoDTO).toList();
    }

    public List<TelefoneDTO> toTelefonesDTO(List<Telefone> telefone) {
        return telefone.stream().map(this::toTelefoneDTO).toList();
    }

    private TelefoneDTO toTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .numero(endereco.getNumero())
                .build();
    }
}
