package br.com.dsi.dtos;

import br.com.dsi.enums.Role;

public record UserRegisterDto(String login, String password, Role role) {}
