package net.kranki.udemyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kranki.udemyApp.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    
    Usuario findByUsername(String username);

}
