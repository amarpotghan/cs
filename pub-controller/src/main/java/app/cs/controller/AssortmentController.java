package app.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.assortment.AssortmentInteractions;
import app.cs.interfaces.model.Assortment;

@Controller
public class AssortmentController {

	private AssortmentInteractions assortmentInteractions;

	public AssortmentController(AssortmentInteractions assortmentInteractions) {

		this.assortmentInteractions = assortmentInteractions;
	}

	@RequestMapping(value = "/assortment/create/{name}/{path}")
	public void create(@RequestBody Assortment assortment,
			@PathVariable String name, @PathVariable String path) {

		assortmentInteractions.create(assortment, path);

	}

}
