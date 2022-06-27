package net.kranki.udemyApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kranki.udemyApp.model.Categoria;
// import org.springframework.data.repository.CrudRepository;

public interface CategoriasRepository extends JpaRepository<Categoria,Integer> {
}
