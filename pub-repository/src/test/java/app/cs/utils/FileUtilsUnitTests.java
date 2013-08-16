package app.cs.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileUtilsUnitTests {
	private FileUtils fileUtils;

	@Test
	public void itShouldReadTheJsonFile() throws IOException,
			URISyntaxException {
		// given

		String filePath = "home/elements.json";
		// when

		fileUtils = new FileUtils();
		String actualContents = fileUtils.getFileContents(filePath);
		// then
		assertThat(actualContents).contains("id");

	}

	@Test
	public void itShouldReadTheFileFromGivenRealPath() throws IOException,
			URISyntaxException {

		// given
		fileUtils = new FileUtils();
		String filePath = "file://C:/UsersCS11/cs/pub-controller/src/main/webapp/WEB-INF/graphics/tacks/ScreenMapping.json";
		File file = new File(filePath);
		URI uri = new URI(filePath);
		// when

		String actualContents = fileUtils.getFileContentsFromGivenPath(uri);
		// then
		assertThat(actualContents).isNotNull();
	}
}
