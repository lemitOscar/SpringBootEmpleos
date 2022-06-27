/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.kranki.udemyApp.service.db;

import java.util.List;
import net.kranki.udemyApp.model.Usuario;
import net.kranki.udemyApp.repository.UsuariosRepository;
import net.kranki.udemyApp.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gogle
 */
@Service
public class IUsuariosServiceJpa implements IUsuariosService{
    
    @Autowired
    private UsuariosRepository ur;

    @Override
    public void guardar(Usuario usuario) {
      ur.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario buscarporUsuario(String username) {
       return  ur.findByUsername(username);
    }
    
}
