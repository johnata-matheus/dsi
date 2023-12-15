package br.com.dsi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsi.models.Veiculo;
import br.com.dsi.services.VeiculoService;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
  
  @Autowired
  VeiculoService veiculoService;

  @GetMapping
  public List<Veiculo> getAllVeiculos(){
    return veiculoService.getAllVeiculos();
  }

  @GetMapping("{id}")
  public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id){
    return veiculoService.findVeiculoById(id);
  }

  @PostMapping
  public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo){
    Veiculo criar = veiculoService.createVeiculo(veiculo); 
    return ResponseEntity.status(HttpStatus.CREATED).body(criar);
  }

  @PutMapping("{id}")
  public ResponseEntity<Veiculo> updateVeiculoById(@PathVariable(value = "id") Long id, @RequestBody Veiculo veiculo ){
    return veiculoService.updateVeiculoById(id, veiculo);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> deleteVeiculoById(@PathVariable Long id){
    return veiculoService.deleteVeiculoById(id);
  }

}
