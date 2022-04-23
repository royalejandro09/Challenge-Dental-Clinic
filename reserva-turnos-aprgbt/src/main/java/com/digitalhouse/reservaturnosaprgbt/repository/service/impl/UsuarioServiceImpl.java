package com.digitalhouse.reservaturnosaprgbt.repository.service.impl;

import com.digitalhouse.reservaturnosaprgbt.entity.Usuario;
import com.digitalhouse.reservaturnosaprgbt.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    /**
     * Injection of dependency
     */
    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     * Metodos
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findOneByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuario no existe", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        usuario.getRoles().forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        });

        UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), roles);

        return userDetails;
    }
}
