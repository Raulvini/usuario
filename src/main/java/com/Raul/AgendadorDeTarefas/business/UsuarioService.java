package com.Raul.AgendadorDeTarefas.business;

import com.Raul.AgendadorDeTarefas.business.converter.UsuarioConverter;
import com.Raul.AgendadorDeTarefas.business.dto.UsuarioDTO;
import com.Raul.AgendadorDeTarefas.infrastructure.exceptions.ConflictException;
import com.Raul.AgendadorDeTarefas.infrastructure.repostitory.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioConverter usuarioConverter;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {

        emailExiste(usuarioDTO.getEmail());

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        var usuario = usuarioConverter.toUsuario(usuarioDTO);
        return usuarioConverter.toUsuarioDTO(usuarioRepository.save(usuario));
    }


    public void emailExiste(String email) {
        try{
            var existe = verificarEmailExistente(email);
            if(existe){
                throw new ConflictException("Email ja cadastrado "+ email);
            }

        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado ",e.getCause());
        }
    }

    public boolean verificarEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }


}
