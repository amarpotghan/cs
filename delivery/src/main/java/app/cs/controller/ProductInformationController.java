package app.cs.controller;

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

import app.cs.actions.contentplanning.pim.PIMInteractions;
import app.cs.model.response.PIMNode;

@Controller
public class ProductInformationController {
	private PIMInteractions productInteractions;

	@Autowired
	public ProductInformationController(PIMInteractions productInteractions) {
		this.productInteractions = productInteractions;
	}

	@RequestMapping(value = { "/pim/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMNode> listForGivenId(@PathVariable String id)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		return new ObjectMapper().readValue(
				formatProductsToJson(productInteractions.getProducts(id)),
				new TypeReference<List<PIMNode>>() {
				});

	}

	@RequestMapping(value = { "/pim/list" }, method = RequestMethod.GET)
	public @ResponseBody
	List<PIMNode> list() throws JsonParseException, JsonMappingException,
			IOException, ParseException {
		return new ObjectMapper().readValue(
				formatProductsToJson(productInteractions.getProducts(null)),
				new TypeReference<List<PIMNode>>() {
				});

	}

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
