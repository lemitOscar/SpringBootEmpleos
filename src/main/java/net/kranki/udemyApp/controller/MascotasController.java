package net.kranki.udemyApp.controller;

import net.kranki.udemyApp.model.Mascota;
import net.kranki.udemyApp.service.db.IMascotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controler para manipular mascotas
 *
 * @author Oscar
 */
@Controller
@RequestMapping("/mascotas")
public class MascotasController {

    @Autowired
    private IMascotasService iMascotasService;

    /**
     * *|CURSOR_MARCADOR|*
     *
     * @param mascota The object that will be used to store the data entered in the form.
     * @return The formPets.html
     */
    @GetMapping("/save")
    private String savePets(Mascota mascota) {//si tiene uso porque si no no carga el form
        // tambien lo ocupo cuando resive el update
        return "/mascotas/formPets";
    }

    /**
     * The function SavePets() is a POST request that takes in a Mascota object and saves it to the database
     *
     * @param mascota This is the object that will be used to save the data.
     * @return A redirect to the root path.
     */
    @PostMapping("/save")
    private String SavePets(Mascota mascota) {
        iMascotasService.savePet(mascota);
        return "redirect:/mascotas/table";
    }

    /**
     * It returns a view with all information of mascotas
     *
     * @param model This is the model object that is used to pass data from the controller to the view.
     * @return A list of all the pets in the database.
     */

    @GetMapping("/table")
    private String table(Model model) {
        model.addAttribute("listMascotas", iMascotasService.getAllPets());
        return "/mascotas/MascotaTable";
    }

    /**
     * It deletes a pet from the database.
     *
     * @param id The id of the pet to be deleted.
     * @return A String
     */
    @GetMapping("/delete/{id}")
    private String deletePet(@PathVariable("id") Integer id) {
        iMascotasService.deletePet(id);
        return "redirect:/mascotas/table";
    }

    @GetMapping("/update/{id}")
    private String updatePet(@PathVariable("id") Integer id, Model model) {
        Mascota mascotaFind = iMascotasService.getIdPet(id);
        model.addAttribute("mascota", mascotaFind);
        return "/mascotas/formPets";
    }

}
