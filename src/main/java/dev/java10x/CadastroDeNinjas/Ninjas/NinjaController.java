package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO ninjaDTO = this.ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninjaDTO.getNome());
    }

    @GetMapping("/listar")
    public List<NinjaDTO> listarNinjas() {
        return this.ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id) {
        NinjaDTO ninja = this.ninjaService.listarNinjaPorId(id);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
        NinjaDTO ninjaSalvo = this.ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninjaSalvo != null){
            return ResponseEntity.ok(ninjaSalvo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com ID: " + id + " não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if (ninjaService.listarNinjaPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com o ID: " + id + " Deletado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com ID: " + id + " não encontrado");
    }
}
