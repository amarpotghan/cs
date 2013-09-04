package app.cs.controller.contentplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

public class PIMSearchController {

	private Interactor pimSearch;

	private StringRequest request;

	@Autowired
	public PIMSearchController(Interactor pimSearch, StringRequest request) {
		this.pimSearch = pimSearch;
		this.request = request;
	}

	@RequestMapping(value = { "/pim/search/{key}" }, method = RequestMethod.GET)
	public @ResponseBody
	String searchPIM(@PathVariable String key) {
		request.setStringRequest(key);
		return ((StringResponse) pimSearch.execute(request))
				.getResponseString();

	}

}
