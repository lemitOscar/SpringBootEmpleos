package net.kranki.udemyApp.service.db;

import net.kranki.udemyApp.model.Vacante;
import net.kranki.udemyApp.repository.VacantesRepository;
import net.kranki.udemyApp.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Primary
public class VacanteServiceJpa implements IVacantesService {

    @Autowired
    private VacantesRepository vacantesRepo;

    @Override
    public List<Vacante> buscarTodos() {
        return vacantesRepo.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {
        return vacantesRepo.findById(idVacante).orElse(null);
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepo.save(vacante);
    }

    @Override
    public List<Vacante> buscarDestacada() {
        return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "aprobada");
    }

    @Override
    public void eliminar(Integer id) {
        vacantesRepo.deleteById(id);
    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepo.findAll(example);
    }

    @Override
    public Page<Vacante> buscarTodas(Pageable page) {
        return vacantesRepo.findAll(page);
    }
}
