package net.kranki.udemyApp.controller;

import net.kranki.udemyApp.model.Perfil;
import net.kranki.udemyApp.model.Usuario;
import net.kranki.udemyApp.model.Vacante;
import net.kranki.udemyApp.service.ICategoriasService;
import net.kranki.udemyApp.service.IUsuariosService;
import net.kranki.udemyApp.service.IVacantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    //injection
    @Autowired
    private ICategoriasService ics;
    @Autowired
    private IVacantesService iVacantesService;
    @Autowired
    private IUsuariosService repoUser;
    @Autowired
    private PasswordEncoder pscoder;

    @GetMapping("/")
    public String inicio(Model model) {
        return "home";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = new Vacante();
        vacante.setNombre("ingeniero de software");
        vacante.setDescripcion("se solicita inge de soporte");
        vacante.setFecha(new Date());
        vacante.setSalario(9700.0);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista = iVacantesService.buscarTodos();
        model.addAttribute("vacante", lista);
        return "tabla";
    }

    @ModelAttribute
    public void setGetGenericos(Model model) {
        Vacante vacaSearch = new Vacante();
        vacaSearch.setearImg();
        model.addAttribute("vacante", iVacantesService.buscarDestacada());
        model.addAttribute("categories", ics.buscarTodas());
        model.addAttribute("buscador", vacaSearch);

    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("buscador") Vacante v, Model m) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Vacante> e = Example.of(v, matcher);
        List<Vacante> lista = iVacantesService.buscarByExample(e);
        m.addAttribute("vacante", lista);
        return "home";
    }

    @InitBinder
    public void iniciarCadena(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

//    USUARIOS Y LOGIN
    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

        String passPlane = usuario.getPassword();
        String passCifra = pscoder.encode(passPlane);

        usuario.setPassword(passCifra);

        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());
        // Creamos el Perfil que le asignaremos al usuario nuevo
        Perfil perfil = new Perfil();
        perfil.setId(3); // Perfil USUARIO
        usuario.agregar(perfil);
        repoUser.guardar(usuario);
//                System.out.println("hi!------> "+ usuario);
        // Ejercicio.

        return "redirect:/";
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication authentication, HttpSession session) {

        //asi obtengo el nombre del ususario nomas
        String nameUser = authentication.getName();

        //saber que rol tiene
        for (GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println("el rol es: " + rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null) {
            Usuario usuario = repoUser.buscarporUsuario(nameUser);
            usuario.setPassword(null);
            System.out.println("Usuario : " + usuario);
            session.setAttribute("usuario", usuario);
        }

        return "redirect:/";
    }

    //utileria para cifrar password si ya esta registrado y no esta cifrada
    @GetMapping("/cifra/{text}")
    @ResponseBody
    public String cifrador(@PathVariable("text") String text) {
        return text + "  cifrada es : " + pscoder.encode(text);
    }

    //login de la aplicacion
    @GetMapping("/login")
    public String hola() {
        return "formLogin";
    }

    //logout para matar la sesion de la app
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler   = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect: /login";
    }

}// fin del controlador
