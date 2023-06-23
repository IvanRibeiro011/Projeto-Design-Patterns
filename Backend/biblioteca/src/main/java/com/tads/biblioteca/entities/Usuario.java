package com.tads.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;
    @Column(unique = true)
    private String matricula;
    @ManyToMany
    @JoinTable(name = "tb_usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Aluguel> alugueis = new ArrayList<>();

    public void addRole(Role role){
        roles.add(role);
    }
    public boolean hasRole(String roleName){
        for (Role role: roles){
            if(role.getAuthority().equals(roleName)){
                return true;
            }
        }
        return false;
    }
}
