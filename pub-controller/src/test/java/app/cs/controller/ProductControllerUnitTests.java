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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;

import app.cs.actions.contentplanning.pim.PIMInteractions;
import app.cs.model.PIMNode;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUnitTests {

	private ProductInformationController productController;

	@Mock
	private PIMInteractions productInteractions;

	@Before
	public void setUp() {
		productController = new ProductInformationController(
				productInteractions);

	}

	@Test
	public void itShouldReturnProductDataFromPIM() throws JsonParseException,
			JsonMappingException, IOException, ParseException {

		// given
		String id = "62";

		String result = "{\"62\":{\"id\":\"62\",\"label\":\"Media Player\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"description\":\"89,00, Blue\",\"service\":\"pim\"},\"91\":{\"id\":\"91\",\"label\":\"Books\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":\"true\",\"description\":\"24,80, white\",\"service\":\"pim\"},\"63\":{\"id\":\"63\",\"label\":\"Accessories\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"service\":\"pim\"}}";
		String expectedResult = "[PIMNode [id=91, title=Books, isfolder=true, type=item, image=image, description=24,80, white, service=pim, isLazy=false], PIMNode [id=62, title=Media Player, isfolder=true, type=item, image=image, description=89,00, Blue, service=pim, isLazy=false], PIMNode [id=63, title=Accessories, isfolder=true, type=item, image=image, description=null, service=pim, isLazy=false]]";
		// when
		when(productInteractions.getProducts(id)).thenReturn(result);

		List<PIMNode> actualResult = productController.listForGivenId(id);
		// then

		verify(productInteractions).getProducts(id);

		assertThat(actualResult).isNotEmpty();
		assertThat(actualResult.toString()).isEqualTo(expectedResult);
	}

	@Test
	public void itShouldReturnProductDataFromPIMWhenIdIsNotGiven()
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {

		// given

		String result = "{\"62\":{\"id\":\"62\",\"label\":\"Media Player\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"description\":\"89,00, Blue\",\"service\":\"pim\"},\"91\":{\"id\":\"91\",\"label\":\"Books\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":\"true\",\"description\":\"24,80, white\",\"service\":\"pim\"},\"63\":{\"id\":\"63\",\"label\":\"Accessories\",\"type\":\"item\",\"image\":\"image\",\"isfolder\":true,\"service\":\"pim\"}}";
		String expectedResult = "[PIMNode [id=91, title=Books, isfolder=true, type=item, image=image, description=24,80, white, service=pim, isLazy=false], PIMNode [id=62, title=Media Player, isfolder=true, type=item, image=image, description=89,00, Blue, service=pim, isLazy=false], PIMNode [id=63, title=Accessories, isfolder=true, type=item, image=image, description=null, service=pim, isLazy=false]]";
		// when
		when(productInteractions.getProducts(null)).thenReturn(result);

		List<PIMNode> actualResult = productController.list();
		// then

		verify(productInteractions).getProducts(null);
		assertThat(actualResult).isNotEmpty();
		assertThat(actualResult.toString()).isEqualTo(expectedResult);

	}

	@Test
	public void itShouldConvertJSONStringToPIMNodeArray()
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		// given

		// when

		// then

	}
}
