package net.kranki.udemyApp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.kranki.udemyApp.model.Categoria;

@Service
public class ICategoriasServiceImpl implements ICategoriasService {

    private List<Categoria> list = null;

    public ICategoriasServiceImpl() {
        list = new LinkedList<Categoria>();
        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setNombre("Desarrollo");
        categoria1.setDescripcion("nose");
        list.add(categoria1);

        Categoria categoria2 = new Categoria();
        categoria2.setId(2);
        categoria2.setNombre("otro desarrollo");
        categoria2.setDescripcion("nose");
        list.add(categoria2);
    }

    @Override
    public void guardar(Categoria categoria) {
        list.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return list;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for (Categoria v : list) {
            if (v.getId() == idCategoria) {
                return v;
            }
        }
        return null;
    }

	@Override
	public void delteCate(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
