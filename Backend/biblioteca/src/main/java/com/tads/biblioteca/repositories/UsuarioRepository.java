package com.tads.biblioteca.repositories;

import com.tads.biblioteca.dtos.UsuarioDTO;
import com.tads.biblioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select new Usuario(obj.email,obj.password,obj.roles) from Usuario obj where obj.email = :email")
    List<UsuarioDTO> searchUserAndRolesByEmail(String email);
}
