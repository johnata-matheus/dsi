package br.com.dsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsi.dtos.LoginReponseDto;
import br.com.dsi.dtos.UserAuthenticationDto;
import br.com.dsi.dtos.UserRegisterDto;
import br.com.dsi.models.User;
import br.com.dsi.repositories.UserRepository;
import br.com.dsi.security.TokenService;

@RestController
@RequestMapping("/user")
public class UserController {
  
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired 
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginReponseDto> login(@RequestBody UserAuthenticationDto data){
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());
    return ResponseEntity.ok(new LoginReponseDto(token));
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody UserRegisterDto data){
    if(userRepository.findByLogin(data.login()) != null){
      return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.login(), encryptedPassword, data.role());

    userRepository.save(newUser);

    return ResponseEntity.ok().build();
  }
}
