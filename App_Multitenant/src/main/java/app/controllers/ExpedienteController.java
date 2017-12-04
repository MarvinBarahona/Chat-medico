package app.controllers;

import app.models.Expediente;
import app.repository.ExpedienteRepository;
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
public class ExpedienteController {
    @Autowired
	private ExpedienteRepository expedienteRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/expediente")
	public String expedientes(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("expediente", new Expediente());
		model.addAttribute("expedientes", expedienteRepository.findAll());
                return "expedientes";
	}

	@RequestMapping(value = "expediente/add", method = RequestMethod.POST)
	@Transactional
	public String addExpediente(@ModelAttribute Expediente expediente, Model model) {
		expedienteRepository.save(expediente);
		return "redirect:{/tenant_id}/expediente";
	}     
}
