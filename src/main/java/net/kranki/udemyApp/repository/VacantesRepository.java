package net.kranki.udemyApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kranki.udemyApp.model.Vacante;



public interface VacantesRepository extends JpaRepository<Vacante, Integer>{
	
	List<Vacante> findByEstatus(String estatus);
	
	//buscar por destacado y estatus and ordena por id y regresa de manera desendente
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer descado,String estatus);
	
	//buscar por un rango de salarios
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1,double s2);
	
	//usando in
	List<Vacante> findByEstatusIn(String [] estatus);
}
