package com.tads.biblioteca.projections;

public interface UsuarioProjection {
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
