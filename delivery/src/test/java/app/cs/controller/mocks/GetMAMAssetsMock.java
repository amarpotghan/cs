package app.cs.controller.mocks;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.boundary.delivery.Interactor;
import app.cs.model.request.StringRequest;
import app.cs.model.response.MAMNode;
import app.cs.model.response.StringResponse;

public class GetMAMAssetsMock {
	
	private Interactor getMAMAssets;

	private StringRequest request;

	@Autowired
	public GetMAMAssetsMock(Interactor getMAMAssets, StringRequest request) {
		this.getMAMAssets = getMAMAssets;
		this.request = request;
	}

	@RequestMapping(value = { "/mam/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<MAMNode> listForGivenId(@PathVariable String id) throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		request.setStringRequest(id);
		return new ObjectMapper().readValue(
				formatProductsToJson(((StringResponse) getMAMAssets
						.execute(request)).getResponseString()),
				new TypeReference<List<MAMNode>>() {
				});

	}

	@RequestMapping(value = { "/mam/list" }, method = RequestMethod.GET)
	public @ResponseBody
	List<MAMNode> list() throws JsonParseException, JsonMappingException, IOException,
	ParseException { 
		request.setStringRequest("");
		return new ObjectMapper().readValue(
				formatProductsToJson(((StringResponse) getMAMAssets
						.execute(request)).getResponseString()),
				new TypeReference<List<MAMNode>>() {
				});

	}
	
	private String formatProductsToJson(String products) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(products);
		JSONArray array = new JSONArray();
		
		for (Object object : jsonObject.keySet()) {
			array.add(jsonObject.get(object));

		}
		return array.toString();
	}
}
