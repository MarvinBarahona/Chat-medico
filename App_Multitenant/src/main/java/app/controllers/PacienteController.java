package app.controllers;


import app.models.Paciente;
import app.repository.PacienteRepository;
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
public class PacienteController {
        @Autowired
	private PacienteRepository pacienteRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/paciente")
	public String pacientes(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("paciente", new Paciente());
		model.addAttribute("pacientes", pacienteRepository.findAll());
                return "pacientes";
	}

	@RequestMapping(value = "paciente/add", method = RequestMethod.POST)
	@Transactional
	public String addPaciente(@ModelAttribute Paciente paciente, Model model) {
		pacienteRepository.save(paciente);
		return "redirect:{/tenant_id}/paciente";
	}  
}
