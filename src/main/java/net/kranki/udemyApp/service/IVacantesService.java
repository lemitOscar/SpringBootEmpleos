package net.kranki.udemyApp.service;

import java.util.List;

import net.kranki.udemyApp.model.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVacantesService {

    List<Vacante> buscarTodos();

    Vacante buscarPorId(Integer idVacante);

    void guardar(Vacante vacante);

    List<Vacante>buscarDestacada();

    void eliminar(Integer id);
    
    List<Vacante> buscarByExample(Example<Vacante> example);
    
    Page<Vacante>buscarTodas(Pageable page);
}
