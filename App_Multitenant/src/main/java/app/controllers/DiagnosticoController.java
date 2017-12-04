package app.controllers;

import app.models.Diagnostico;
import app.repository.DiagnosticoRepository;
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
public class DiagnosticoController {
    @Autowired
	private DiagnosticoRepository diagnosticoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/diagnostico")
	public String diagnosticos(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("diagnostico", new Diagnostico());
		model.addAttribute("diagnosticos", diagnosticoRepository.findAll());
                return "diagnosticos";
	}

	@RequestMapping(value = "diagnostico/add", method = RequestMethod.POST)
	@Transactional
	public String addDiagnostico(@ModelAttribute Diagnostico diagnostico, Model model) {
		diagnosticoRepository.save(diagnostico);
		return "redirect:{/tenant_id}/diagnostico";
	}     
}
