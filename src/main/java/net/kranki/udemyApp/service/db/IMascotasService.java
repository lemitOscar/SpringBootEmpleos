package net.kranki.udemyApp.service.db;

import net.kranki.udemyApp.model.Mascota;

import java.util.List;

public interface IMascotasService {

    void savePet(Mascota m);

    List<Mascota> getAllPets();

    Mascota getIdPet(Integer id);

    void deletePet(Integer id);
}
