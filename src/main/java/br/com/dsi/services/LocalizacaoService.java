package br.com.dsi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.dsi.models.Localizacao;
import br.com.dsi.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {
  
  @Autowired
  LocalizacaoRepository localizacaoRepository;

  public List<Localizacao> getAllLocalizacao(){
    List<Localizacao> localizacoes = localizacaoRepository.findAll();
    return localizacoes;
  }

  public ResponseEntity<Localizacao> findLocalizacaoById(Long id){
    Optional<Localizacao> localizacaoId = localizacaoRepository.findById(id); 
    if(localizacaoId.isPresent()){
        Localizacao localizacao = localizacaoId.get();
        return ResponseEntity.ok().body(localizacao);
    } else {
        return ResponseEntity.notFound().build();
    }
  }

  public Localizacao createlocalizacao(Localizacao localizacao){
    return localizacaoRepository.save(localizacao);
  }

  public ResponseEntity<Localizacao> updateLocalizacaoById(Long id, Localizacao localizacao){
    return localizacaoRepository.findById(id)
            .map(localizacaoUpdate ->{
                localizacaoUpdate.setLatitude(localizacao.getLatitude());
                localizacaoUpdate.setLongitude(localizacao.getLongitude());
                Localizacao updated = localizacaoRepository.save(localizacaoUpdate);
                return ResponseEntity.ok().body(updated);
            }).orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<Object> deleteLocalizacaoById(Long id){
    return localizacaoRepository.findById(id)
            .map(localizacaoDelete -> {
                localizacaoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
  }

}
