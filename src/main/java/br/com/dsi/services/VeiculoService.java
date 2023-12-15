package br.com.dsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dsi.models.Veiculo;
import br.com.dsi.repositories.VeiculoRepository;

@Service
public class VeiculoService {
  
  @Autowired
  VeiculoRepository veiculoRepository;

  public List<Veiculo> getAllVeiculos(){
    List<Veiculo> veiculos = veiculoRepository.findAll();
    return veiculos;
  }

  public ResponseEntity<Veiculo> findVeiculoById(Long id){
    Optional<Veiculo> veiculoId = veiculoRepository.findById(id); 
    if(veiculoId.isPresent()){
        Veiculo veiculo = veiculoId.get();
        return ResponseEntity.ok().body(veiculo);
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  public Veiculo createVeiculo(Veiculo veiculo){
    return veiculoRepository.save(veiculo);
  }

  public ResponseEntity<Veiculo> updateVeiculoById(Long id, Veiculo veiculo){
    return veiculoRepository.findById(id)
            .map(veiculoUpdate ->{
                veiculoUpdate.setPlaca(veiculo.getPlaca());
                veiculoUpdate.setModelo(veiculo.getModelo());
                veiculoUpdate.setAnoFabricacao(veiculo.getAnoFabricacao());
                Veiculo updated = veiculoRepository.save(veiculoUpdate);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<Object> deleteVeiculoById(Long id){
    return veiculoRepository.findById(id)
            .map(veiculoDelete -> {
                veiculoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
  }
}
