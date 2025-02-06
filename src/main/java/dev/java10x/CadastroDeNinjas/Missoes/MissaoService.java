package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {
    private MissaoRepository missaoRepository;

    private MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missaoRepository, MissaoMapper missaoMapper) {
        this.missaoRepository = missaoRepository;
        this.missaoMapper = missaoMapper;
    }


    public MissaoDTO criarMissao(MissaoDTO missaoDTO){
        MissaoModel missaoSalva = missaoMapper.map(missaoDTO);
        missaoSalva = this.missaoRepository.save(missaoSalva);
        return missaoMapper.map(missaoSalva);
    }

    public List<MissaoDTO> listarMissoes(){
        List<MissaoModel> missoes = this.missaoRepository.findAll();
        return missoes.stream()
                .map(missaoMapper::map)
                .collect(Collectors.toList());
    }

    public MissaoDTO listarMissaoPorId(Long id){
        Optional<MissaoModel> missaoModel = this.missaoRepository.findById(id);
        return missaoModel.map(missaoMapper::map).orElse(null);
    }

    public MissaoDTO atualizarMissao(Long id, MissaoDTO missaoDTO){
        Optional<MissaoModel> missaoExiste = this.missaoRepository.findById(id);
        if (missaoExiste.isPresent()){
            MissaoModel missaoAtualizada = missaoMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissaoModel missaoSalva = this.missaoRepository.save(missaoAtualizada);
            return missaoMapper.map(missaoSalva);
        }
        return null;
    }

    public void deletarMissao(Long id){
        this.missaoRepository.deleteById(id);
    }
}
