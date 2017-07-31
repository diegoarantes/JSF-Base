package com.absoft.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Usuários do sistema
 *
 * @author Diego Arantes Data alteração 15/12/2016
 */
@Entity
@EqualsAndHashCode(of = "id")
public @Data
class Usuario implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String nome;

    @NotNull
    @Column(unique = true, length = 15)
    private String usuario;

    @NotNull
    private String senha;

    @NotNull
    @Column(unique = true, length = 30)
    private String email;

    @NotNull
    private boolean ativo;

    @NotNull
    private boolean online;

    @Column(length = 15)
    private String ipUltimoLogon;

    @Temporal(TemporalType.TIMESTAMP)
    private Date horaUltimoLogon;

    @Column(length = 6)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Permissao> permissoes = new ArrayList<>();
}
