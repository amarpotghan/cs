package app.cs.controller.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.StringResponse;

public class GetMAMAssetController {
	private Interactor getMAMAssets;

	private StringRequest request;

	@Autowired
	public GetMAMAssetController(Interactor getMAMAssets, StringRequest request) {
		this.getMAMAssets = getMAMAssets;
		this.request = request;
	}

	@RequestMapping(value = { "/mam/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	String listForGivenId(@PathVariable String id) {
		request.setStringRequest(id);
		return ((StringResponse) getMAMAssets.execute(request))
				.getResponseString();

	}

	@RequestMapping(value = { "/mam/list" }, method = RequestMethod.GET)
	public @ResponseBody
	String list() {
		request.setStringRequest("");
		return ((StringResponse) getMAMAssets.execute(request))
				.getResponseString();

	}
}
