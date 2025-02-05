package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String rank;

    @OneToMany(mappedBy = "missoes")
    private List<NinjaModel> ninjaModel;

    public MissaoModel() {
    }

    public MissaoModel(Long id, String nome, String rank) {
        this.id = id;
        this.nome = nome;
        this.rank = rank;
    }
}
