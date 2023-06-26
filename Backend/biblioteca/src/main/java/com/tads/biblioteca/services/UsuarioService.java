package com.tads.biblioteca.services;

import com.tads.biblioteca.dtos.UsuarioDTO;
import com.tads.biblioteca.entities.Role;
import com.tads.biblioteca.entities.Usuario;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
import com.tads.biblioteca.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return mapper.map(usuario, UsuarioDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAllPage(Pageable pageable) {
        Page<Usuario> alunos = repository.findAll(pageable);
        return alunos.map(x -> mapper.map(x, UsuarioDTO.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UsuarioDTO> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Usuario user = new Usuario();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for (UsuarioDTO dto : result) {
            user.addRole(new Role(dto.getId(), dto.getRoles().get(0).getAuthority()));
        }
        return user;
    }
}
