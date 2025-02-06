package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return this.ninjaService.criarNinja(ninja);
    }

    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return this.ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjaPorId(@PathVariable Long id) {
        return this.ninjaService.listarNinjaPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public NinjaDTO atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        return this.ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    @GetMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id) {
        this.ninjaService.deletarNinjaPorId(id);
    }
}
