package net.kranki.udemyApp.service.db;


import net.kranki.udemyApp.model.Mascota;
import net.kranki.udemyApp.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotasServiceImpl implements IMascotasService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public void savePet(Mascota m) {
        mascotaRepository.save(m);
    }

    @Override
    public List<Mascota> getAllPets() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota getIdPet(Integer id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePet(Integer id) {
        mascotaRepository.deleteById(id);
    }
}
