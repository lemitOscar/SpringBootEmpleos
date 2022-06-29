package net.kranki.udemyApp.controller;

import net.kranki.udemyApp.model.Categoria;
import net.kranki.udemyApp.service.ICategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {
    @Autowired
    private ICategoriasService iCategoriasService;

    //pagina principal
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Categoria> listCategoria = iCategoriasService.buscarTodas();
        model.addAttribute("listCate", listCategoria);
        return "categorias/listCategorias";
    }

    @GetMapping("/create")
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Categoria categoria) {
        iCategoriasService.guardar(categoria);
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminarCate(@PathVariable("id") int id) {
        iCategoriasService.delteCate(id);
        return "redirect:/categorias/index";
    }

    @GetMapping("/update/{id}")
    public String actualizarCategorias(@PathVariable("id") int id, Model model) {
        Categoria categoria = iCategoriasService.buscarPorId(id);
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }
}
