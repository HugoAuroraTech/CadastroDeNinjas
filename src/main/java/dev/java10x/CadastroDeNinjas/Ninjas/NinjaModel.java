package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoModel;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)

    private String email;

    private Integer idade;

    @Column(name = "img_url")
    private String imgUrl;

    private String rank;

    @ManyToOne
    @JoinColumn(name = "missoes_id")
    private MissaoModel missoes;

}
