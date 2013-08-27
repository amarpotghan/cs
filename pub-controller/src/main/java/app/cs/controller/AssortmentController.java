package app.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import app.cs.actions.contentplanning.assortment.AssortmentInteractions;
import app.cs.impl.model.Assortment;

@Controller
public class AssortmentController {

	private AssortmentInteractions assortmentInteractions;

	@Autowired
	public AssortmentController(AssortmentInteractions assortmentInteractions) {

		this.assortmentInteractions = assortmentInteractions;
	}

	@RequestMapping(value = "/assortment/create/{path}")
	public void create(@RequestBody Assortment assortment,
			@PathVariable String path) {

		assortmentInteractions.create(assortment, path);
	}

	@RequestMapping(value = "/assortment/move/path/{path}/newPath/{newPath}")
	public void move(@RequestBody Assortment assortment,
			@PathVariable String newPath) {
		assortmentInteractions.move(assortment, newPath);

	}
}
