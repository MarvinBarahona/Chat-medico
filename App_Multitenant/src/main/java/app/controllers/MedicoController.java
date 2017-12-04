package app.controllers;

import app.models.Medico;
import app.repository.MedicoRepository;
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
public class MedicoController {
        @Autowired
	private MedicoRepository medicoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/medico")
	public String medicos(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("medico", new Medico());
		model.addAttribute("medicos", medicoRepository.findAll());
                return "medicos";
	}

	@RequestMapping(value = "medico/add", method = RequestMethod.POST)
	@Transactional
	public String addMedico(@ModelAttribute Medico medico, Model model) {
		medicoRepository.save(medico);
		return "redirect:{/tenant_id}/medico";
	}  
}
