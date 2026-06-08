package com.Raul.AgendadorDeTarefas.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "endereco")
@Builder
public class Endereco {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="rua")
    private String rua;
    @Column(name ="numero")
    private Long numero;
    @Column(name ="cidade")
    private String cidade;
    @Column(name ="estado", length=2)
    private String estado;
    @Column(name ="comlemento")
    private String complemento;
    @Column(name = "cep", length=9)
    private String cep;

}
