package app.cs.controller;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.model.CustomResponse;
import app.cs.utils.FileUtils;

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
		CustomResponse actualContents = client.getHome();

		// then

		verify(fileUtils).getFileContents("home.html");
		assertThat(contents).isEqualTo(contents);

	}

}
