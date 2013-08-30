package app.cs.controller.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.actions.contentplanning.assortment.MAMInteractions;

@Controller
public class MAMController {

	private MAMInteractions mamInteractions;

	@Autowired
	public MAMController(MAMInteractions mamInteractions) {
		this.mamInteractions = mamInteractions;
	}

	@RequestMapping(value = { "/mam/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	String listForGivenId(@PathVariable String id) {
		return mamInteractions.getAssets(id);

	}

	@RequestMapping(value = { "/mam/list" }, method = RequestMethod.GET)
	public @ResponseBody
	String list() {
		return mamInteractions.getAssets(null);

	}

}