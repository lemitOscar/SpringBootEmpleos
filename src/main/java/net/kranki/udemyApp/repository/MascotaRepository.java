package net.kranki.udemyApp.repository;


import net.kranki.udemyApp.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota,Integer > {
}
