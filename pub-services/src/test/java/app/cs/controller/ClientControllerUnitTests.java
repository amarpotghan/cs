package app.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.controller.ClientController;
import app.cs.data.business.api.model.ICustomResponse;
import app.cs.utils.FileUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientControllerUnitTests {

	private ClientController client;

	@Mock
	private FileUtils fileUtils;

	private FileUtils test = new FileUtils();

	@Before
	public void setUp() {

		client = new ClientController(fileUtils);
	}

	@Test
	public void itShouldReturnHomeHTMLfile() throws IOException,
			URISyntaxException {
		// given

		String contents = "result";
		JSONObject contentObject = new JSONObject();
		contentObject.put("html", "test");
		when(fileUtils.getFileContents("home.html")).thenReturn(contents);
		// when
		ICustomResponse actualContents = client.getHome();

		// then

		verify(fileUtils).getFileContents("home.html");
		assertThat(contents).isEqualTo(contents);

	}

}
