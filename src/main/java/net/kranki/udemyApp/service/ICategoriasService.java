package net.kranki.udemyApp.service;

import java.util.List;

import net.kranki.udemyApp.model.Categoria;

public interface ICategoriasService {

    void guardar(Categoria categoria);

    List<Categoria> buscarTodas();

    Categoria buscarPorId(Integer idCategoria);

    void delteCate(Integer id);

}
