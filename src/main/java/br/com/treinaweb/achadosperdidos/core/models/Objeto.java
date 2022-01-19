package br.com.treinaweb.achadosperdidos.core.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.treinaweb.achadosperdidos.core.entitylisteners.ObjetoEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(ObjetoEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = false)
    private Boolean entrege;

    @Column(nullable = true, name = "dono_nome")
    private String donoNome;

    @Column(nullable = true, name = "dono_cpf")
    private String donoCpf;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "imagem_id", nullable = true)
    private Imagem imagem;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

}
