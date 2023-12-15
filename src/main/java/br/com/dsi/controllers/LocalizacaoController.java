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

import br.com.dsi.models.Localizacao;
import br.com.dsi.services.LocalizacaoService;

@RestController
@RequestMapping("/localizacao")
public class LocalizacaoController {
  
  @Autowired
  LocalizacaoService localizacaoService;

  @GetMapping
  public List<Localizacao> getAllLocalizacao(){
    return localizacaoService.getAllLocalizacao();
  }

  @GetMapping("{id}")
  public ResponseEntity<Localizacao> getLocalizacaoById(@PathVariable Long id){
    return localizacaoService.findLocalizacaoById(id);
  }

  @PostMapping
  public ResponseEntity<Localizacao> createLocalizacao(@RequestBody Localizacao localizacao){
    Localizacao criar = localizacaoService.createlocalizacao(localizacao); 
    return ResponseEntity.status(HttpStatus.CREATED).body(criar);
  }

  @PutMapping("{id}")
  public ResponseEntity<Localizacao> updateLocalizacaoById(@PathVariable(value = "id") Long id, @RequestBody Localizacao localizacao ){
    return localizacaoService.updateLocalizacaoById(id, localizacao);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> deleteLocalizacaoById(@PathVariable Long id){
    return localizacaoService.deleteLocalizacaoById(id);
  }
}
