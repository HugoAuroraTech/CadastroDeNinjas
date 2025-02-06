package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;


    public NinjaService(NinjaMapper ninjaMapper, NinjaRepository ninjaRepository) {
        this.ninjaMapper = ninjaMapper;
        this.ninjaRepository = ninjaRepository;
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    public List<NinjaModel> listarNinjas() {
        return this.ninjaRepository.findAll();

    }

    public NinjaModel listarNinjaPorId(Long id){
        Optional<NinjaModel> ninjaPorId = this.ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

    public NinjaModel atualizarNinja(Long id, NinjaModel ninjaAtualizado){
        if (this.ninjaRepository.existsById(id)) {
            ninjaAtualizado.setId(id);
            return this.ninjaRepository.save(ninjaAtualizado);
        }
        return null;

    }

    public void deletarNinjaPorId(Long id){
        this.ninjaRepository.deleteById(id);
    }

}
