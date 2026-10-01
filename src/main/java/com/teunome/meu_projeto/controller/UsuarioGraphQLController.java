package com.teunome.meu_projeto.controller;

import com.teunome.meu_projeto.model.Usuario;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class UsuarioGraphQLController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @QueryMapping
    public Usuario obterUsuarioPorId(@Argument Long id) {
        return usuarios.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @QueryMapping
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    @MutationMapping
    public Usuario criarUsuario(@Argument String nome, @Argument String email) {
        Usuario novoUsuario = new Usuario(idCounter.getAndIncrement(), nome, email);
        usuarios.add(novoUsuario);
        return novoUsuario;
    }
}