package app.controllers;


import app.models.Paciente;
import app.repository.PacienteRepository;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/{tenantid}/paciente")
public class PacienteController {
        @Autowired
	private PacienteRepository pacienteRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<Paciente> pacientes(@PathVariable String tenantid) {
                return (Collection<Paciente>) pacienteRepository.findAll();
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	public String addPaciente(@RequestBody Paciente paciente) {
		pacienteRepository.save(paciente);
		return "redirect:/{tenant_id}/paciente";
	}  
}
