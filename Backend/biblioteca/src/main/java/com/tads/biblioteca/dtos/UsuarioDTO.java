package com.tads.biblioteca.dtos;

import com.tads.biblioteca.entities.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private String password;
    private List<RoleDTO> roles = new ArrayList<>();

    public UsuarioDTO(String email, String password, List<RoleDTO> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
