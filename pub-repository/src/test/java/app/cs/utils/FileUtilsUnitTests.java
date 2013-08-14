package app.cs.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;
import org.springframework.core.io.FileSystemResource;

import app.cs.utils.FileUtils;

import static org.fest.assertions.Assertions.*;

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

}
