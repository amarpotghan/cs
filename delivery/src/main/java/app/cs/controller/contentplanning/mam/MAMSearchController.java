package app.cs.controller.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

@Controller
public class MAMSearchController {
	
	private Interactor mamSearch;

	private StringRequest request;

	@Autowired
	public MAMSearchController(Interactor mamSearch, StringRequest request) {
		this.mamSearch = mamSearch;
		this.request = request;
	}

	@RequestMapping(value = { "/mam/search/{key}" }, method = RequestMethod.GET)
	public @ResponseBody
	String searchMAM(@PathVariable String key) {
		request.setStringRequest(key);
		return ((StringResponse) mamSearch.execute(request))
				.getResponseString();

	}

}
