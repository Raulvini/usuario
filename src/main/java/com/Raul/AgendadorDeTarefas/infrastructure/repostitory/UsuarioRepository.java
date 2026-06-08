package com.Raul.AgendadorDeTarefas.infrastructure.repostitory;

import com.Raul.AgendadorDeTarefas.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
