package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missao){
        MissaoDTO missaoDTO = this.missaoService.criarMissao(missao);
        return ResponseEntity.ok("Sucesso ao criar a missão");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarMissoes(){
        List<MissaoDTO> missoes = this.missaoService.listarMissoes();
        if (missoes != null){
            return ResponseEntity.ok(missoes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma missão encontrada");
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id){
        MissaoDTO missaoDTO = this.missaoService.listarMissaoPorId(id);
        if (missaoDTO != null){
            return ResponseEntity.ok(missaoDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com ID: " + id + " não encontrado");
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarMissao(@PathVariable Long id, @RequestBody MissaoDTO missao){
        MissaoDTO missaoDTO = this.missaoService.atualizarMissao(id, missao);
        if (missaoDTO != null){
            return ResponseEntity.ok(missaoDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com ID: " + id + " não encontrado");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if (this.missaoService.listarMissaoPorId(id) != null){
            this.missaoService.deletarMissao(id);
            return ResponseEntity.ok("Missão deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com ID: " + id + " não encontrado");
    }

}
