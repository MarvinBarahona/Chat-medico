package app.controllers;

import app.models.Receta;
import app.repository.RecetaRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{tenantid}")
public class RecetaController {
        @Autowired
	private RecetaRepository recetaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/receta")
	public String recetas(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("receta", new Receta());
		model.addAttribute("recetas", recetaRepository.findAll());
                return "recetas";
	}

	@RequestMapping(value = "receta/add", method = RequestMethod.POST)
	@Transactional
	public String addReceta (@ModelAttribute Receta receta, Model model) {
		recetaRepository.save(receta);
		return "redirect:{/tenant_id}/receta";
	} 
}
