package net.kranki.udemyApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.kranki.udemyApp.model.Vacante;
import net.kranki.udemyApp.service.ICategoriasService;
import net.kranki.udemyApp.service.IVacantesService;
import net.kranki.udemyApp.util.Utileria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/*
    *este es un controlador para manejar la sección de vacantes
 */
@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Value("${udemyapp.ruta.imagenes}")
    private String ruta;

    @Autowired
    private IVacantesService iVacantesService;

    @Autowired
    private ICategoriasService iCategoriasService;

    @GetMapping("/index")
    public String homeVacante(Model model) {
        List<Vacante> lista = iVacantesService.buscarTodos();
        model.addAttribute("vaca", lista);
        return "vacantes/listVacantes";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable(name = "id") int idVacante, Model model) {
        Vacante vacante = iVacantesService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
        iVacantesService.eliminar(id);
        attributes.addFlashAttribute("msg", "Vacante Eliminada");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {

        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
            @RequestParam("archivoImagen") MultipartFile multipartFile) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("ocurrio un error " + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }

        if (!multipartFile.isEmpty()) {
            // String ruta = "/empleos/img-vacantes/"; // Linux/MAC
            // String ruta = "C:/empleos/img-vacantes/"; // Windows
            String nombreImagen = Utileria.guardarArchivo(multipartFile, ruta);
            if (nombreImagen != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImagen(nombreImagen);
            }
        }

        iVacantesService.guardar(vacante);
        attributes.addFlashAttribute("msg", "Guardado con exito");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/edit/{id}")
    public String updateVaca(@PathVariable("id") int id, Model model) {
        Vacante vacante = iVacantesService.buscarPorId(id);
        model.addAttribute("vacante", vacante);
        return "vacantes/formVacante";
    }

    //para reciclar codigo
    @ModelAttribute
    public void setGenerico(Model model) {
        model.addAttribute("categorias", iCategoriasService.buscarTodas());
    }

    // configuracion de fecha siempre en un formulario
    @InitBinder
    public void configFecha(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    //paginacion
    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> lista = iVacantesService.buscarTodas(page);
        model.addAttribute("vaca", lista);
        return "vacantes/listVacantes";
    }
}
