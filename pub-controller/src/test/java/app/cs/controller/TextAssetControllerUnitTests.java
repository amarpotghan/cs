package app.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.model.response.ClientResponse;
import app.cs.utils.FileUtils;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.fest.assertions.Assertions.assertThat;

//import app.cs.text.TextAssetInteractions;

@RunWith(MockitoJUnitRunner.class)
public class TextAssetControllerUnitTests {

	@Mock
	private FileUtils fileUtils;

	private TextAssetController textAssetController;

	private String id;

	@Before
	public void setUp() {
		textAssetController = new TextAssetController(fileUtils);
	}

	@Test
	public void itShouldReturnTextDataFromTextAssets() throws IOException,
			URISyntaxException {

		String contents = "result";
		when(textAssetController.getTextAssets("textassets.json"))
				.thenReturn(contents);
		// when
		String actualContents = textAssetController.getTextAssets(id);

		// then

		verify(fileUtils).getFileContents("textassets.json");
		assertThat(contents).isEqualTo(actualContents);
	}

}