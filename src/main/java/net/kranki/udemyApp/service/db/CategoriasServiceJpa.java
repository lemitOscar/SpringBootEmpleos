package net.kranki.udemyApp.service.db;

import net.kranki.udemyApp.model.Categoria;
import net.kranki.udemyApp.repository.CategoriasRepository;
import net.kranki.udemyApp.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepo;

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {

		Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}
	/*
	 * ANOTACION PRIMARY
	 *
	 * que implementacion de interfaz vamos a utilizar en nuestra aplicacion
	 * ANOTACION QUALIFIER ES LOS MISMO PERO EN qualifier tengo que escrbir su clase
	 * para que funcione
	 */

	@Override
	public void delteCate(Integer id) {
		categoriasRepo.deleteById(id);
	}
}
