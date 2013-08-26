package app.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.pim.PIMInteractions;

@Controller
public class ProductInformationController {
	private PIMInteractions productInteractions;

	@Autowired
	public ProductInformationController(PIMInteractions productInteractions) {
		this.productInteractions = productInteractions;
	}

	@RequestMapping(value = { "/pim/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	String listForGivenId(@PathVariable String id) {
		return productInteractions.getProducts(id);

	}

	@RequestMapping(value = { "/pim/list" }, method = RequestMethod.GET)
	public @ResponseBody
	String list() {
		return productInteractions.getProducts(null);

	}
}
