package com.tads.biblioteca.services;

import com.tads.biblioteca.config.AuthorizationServerConfig;
import com.tads.biblioteca.dtos.UsuarioDTO;
import com.tads.biblioteca.entities.Role;
import com.tads.biblioteca.entities.Usuario;
import com.tads.biblioteca.exceptions.EntityRegistrationException;
import com.tads.biblioteca.exceptions.ResourceNotFoundException;
import com.tads.biblioteca.projections.UsuarioProjection;
import com.tads.biblioteca.repositories.RoleRepository;
import com.tads.biblioteca.repositories.UsuarioRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private RoleRepository roleRepository;
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
        List<UsuarioProjection> result = repository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        Usuario user = new Usuario();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for (UsuarioProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioDTO dto) {
    try{
        if (Boolean.TRUE.equals(repository.existsByEmail(dto.getEmail()))) {
            throw new EntityRegistrationException("Já existe um usuário cadastrado com este email.");
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setPassword(encoder.encode(dto.getPassword()));
        usuario.addRole(roleRepository.findRoleByName("ROLE_ALUNO"));
        return mapper.map(repository.save(usuario), UsuarioDTO.class);
    }
    catch (ConstraintViolationException e){
        throw new EntityRegistrationException("Erro interno");
    }
    }
}
