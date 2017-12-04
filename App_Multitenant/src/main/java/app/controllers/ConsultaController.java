package app.controllers;

import app.models.Consulta;
import app.repository.ConsultaRepository;
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
public class ConsultaController {
    @Autowired
	private ConsultaRepository consultaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/consulta")
	public String consultas(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("consulta", new Consulta());
		model.addAttribute("consultas", consultaRepository.findAll());
                return "consultas";
	}

	@RequestMapping(value = "consulta/add", method = RequestMethod.POST)
	@Transactional
	public String addConsulta(@ModelAttribute Consulta consulta, Model model) {
		consultaRepository.save(consulta);
		return "redirect:{/tenant_id}/consulta";
	}     
}
