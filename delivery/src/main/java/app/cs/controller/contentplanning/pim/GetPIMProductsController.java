package app.cs.controller.contentplanning.pim;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.PIMNode;
import app.cs.model.response.StringResponse;

@Controller
public class GetPIMProductsController {
	private Interactor getPIMAssets;
	private StringRequest request;

	@Autowired
	public GetPIMProductsController(Interactor getPIMAssets,
			StringRequest request) {
		this.getPIMAssets = getPIMAssets;
		this.request = request;
	}

	@RequestMapping(value = { "/pim/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMNode> listForGivenId(@PathVariable String id)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		request.setStringRequest(id);
		return new ObjectMapper().readValue(
				formatProductsToJson(((StringResponse) getPIMAssets
						.execute(request)).getResponseString()),
				new TypeReference<List<PIMNode>>() {
				});

	}

	@RequestMapping(value = { "/pim/list" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMNode> list() throws JsonParseException, JsonMappingException,
			IOException, ParseException {
		request.setStringRequest("");
		return new ObjectMapper().readValue(
				formatProductsToJson(((StringResponse) getPIMAssets
						.execute(request)).getResponseString()),
				new TypeReference<List<PIMNode>>() {
				});

	}

	@SuppressWarnings("unchecked")
	private String formatProductsToJson(String products) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(products);
		JSONArray array = new JSONArray();
		System.out.println(array.size());
		for (Object object : jsonObject.keySet()) {
			array.add(jsonObject.get(object));

		}
		return array.toString();
	}
}
