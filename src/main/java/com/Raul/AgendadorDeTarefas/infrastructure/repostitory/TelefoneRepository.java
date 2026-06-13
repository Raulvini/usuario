package com.Raul.AgendadorDeTarefas.infrastructure.repostitory;

import com.Raul.AgendadorDeTarefas.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {


}
