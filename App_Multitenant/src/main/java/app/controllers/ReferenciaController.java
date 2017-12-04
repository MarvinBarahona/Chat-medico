package app.controllers;

import app.models.Referencia;
import app.repository.ReferenciaRepository;
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
public class ReferenciaController {
    @Autowired
	private ReferenciaRepository referenciaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(value = "/referencia")
	public String referencias(@PathVariable String tenantid, Model model) {
		model.addAttribute("tenantid", tenantid);
		model.addAttribute("referencia", new Referencia());
		model.addAttribute("referencias", referenciaRepository.findAll());
                return "referencias";
	}

	@RequestMapping(value = "referencia/add", method = RequestMethod.POST)
	@Transactional
	public String addReferencia(@ModelAttribute Referencia referencia, Model model) {
		referenciaRepository.save(referencia);
		return "redirect:{/tenant_id}/referencia";
	}
}
