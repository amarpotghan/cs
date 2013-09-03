package app.cs.integration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import app.cs.impl.model.Assortment;
import app.cs.impl.model.Product;

public class Test {

	@org.junit.Test
	public void itShouldCreateAssortment() throws JsonGenerationException, JsonMappingException, IOException {
		// given
		Assortment assortment = new Assortment();
		Product product = new Product("01", "ipad", "electronics");
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		assortment.setID("01");
		assortment.setProducts(products);

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("D:\\test.json"), assortment);
	}
}
