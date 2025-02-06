package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public NinjaModel criarNinja(NinjaModel ninja){
        return this.ninjaRepository.save(ninja);
    }

    public List<NinjaModel> listarNinjas() {
        return this.ninjaRepository.findAll();

    }

    public NinjaModel listarNinjaPorId(Long id){
        Optional<NinjaModel> ninjaPorId = this.ninjaRepository.findById(id);
        return ninjaPorId.orElse(null);
    }

}
