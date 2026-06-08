package com.Raul.AgendadorDeTarefas.business;

import com.Raul.AgendadorDeTarefas.business.converter.UsuarioConverter;
import com.Raul.AgendadorDeTarefas.business.dto.UsuarioDTO;
import com.Raul.AgendadorDeTarefas.infrastructure.repostitory.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioConverter usuarioConverter;
    private final UsuarioRepository usuarioRepository;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        var usuario = usuarioConverter.toUsuario(usuarioDTO);
        return usuarioConverter.toUsuarioDTO(usuarioRepository.save(usuario));
    }






}
