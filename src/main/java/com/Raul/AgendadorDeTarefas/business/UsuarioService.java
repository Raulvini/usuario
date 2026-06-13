package com.Raul.AgendadorDeTarefas.business;

import com.Raul.AgendadorDeTarefas.business.converter.UsuarioConverter;
import com.Raul.AgendadorDeTarefas.business.dto.EnderecoDTO;
import com.Raul.AgendadorDeTarefas.business.dto.TelefoneDTO;
import com.Raul.AgendadorDeTarefas.business.dto.UsuarioDTO;
import com.Raul.AgendadorDeTarefas.infrastructure.exceptions.ConflictException;
import com.Raul.AgendadorDeTarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.Raul.AgendadorDeTarefas.infrastructure.repostitory.EnderecoRepository;
import com.Raul.AgendadorDeTarefas.infrastructure.repostitory.TelefoneRepository;
import com.Raul.AgendadorDeTarefas.infrastructure.repostitory.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioConverter usuarioConverter;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TelefoneRepository telefoneRepository;
    private final EnderecoRepository enderecoRepository;

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

    @Transactional
    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email){
        var usuario = usuarioRepository.findByEmail(email).
                orElseThrow(()-> new ResourceNotFoundException("Email não encontrado "+ email));

        return usuarioConverter.toUsuarioDTO(usuario);
    }

    public UsuarioDTO atulaizaDadosUsuario(UsuarioDTO usuarioDTO){
        var authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userSpring = (User) authentication;

        assert userSpring != null;
        String email = userSpring.getUsername();

        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        var usuario = usuarioRepository.findByEmail(email).
                orElseThrow(()-> new ResourceNotFoundException("Email não encontrado "+ email));

        var usuarioUpdate = usuarioConverter.updateUsuario(usuarioDTO, usuario);

        return usuarioConverter.toUsuarioDTO(usuarioRepository.save(usuarioUpdate));
    }


    public EnderecoDTO atualizarEndereco(Long idEndereco , EnderecoDTO enderecoDTO){
        var endereco = enderecoRepository.findById(idEndereco).
                orElseThrow(()-> new ResourceNotFoundException("Id não encontrado "+idEndereco));


        var enderecoUpadate = usuarioConverter.updateEndereco(enderecoDTO, endereco);
        return usuarioConverter.toEnderecoDTO(enderecoRepository.save(enderecoUpadate));

    }


    public TelefoneDTO atualizarTelefone(Long idTelefone , TelefoneDTO telefoneDTO){
        var telefone = telefoneRepository.findById(idTelefone).
                orElseThrow(()-> new ResourceNotFoundException("Id não encontrado "+idTelefone));


        var telefoneUpadate = usuarioConverter.updateTelefone(telefoneDTO, telefone);
        return usuarioConverter.toTelefoneDTO(telefoneRepository.save(telefoneUpadate));

    }
}
