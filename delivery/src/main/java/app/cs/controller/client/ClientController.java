package app.cs.controller.client;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.model.response.ClientResponse;
import app.cs.utils.FileUtils;

/**
 * The Class ClientController.
 */
@Controller
public class ClientController {

	private String SUBCONTEXT;
	private final String MRMINDEX = "redirect:/pages/mrm/engine/core/html/start.html";
	private final String PUBINDEX = "redirect:/pages/pub/engine/core/html/start.html";

	@Autowired
	private ServletContext context;
	private FileUtils fileUtils;

	@Autowired
	public ClientController(FileUtils fileUtils) {
		this.fileUtils = fileUtils;
	}

	@RequestMapping(value = { "/mrm" }, method = RequestMethod.GET)
	public String getMrmIndexPage() throws ParseException, IOException,
			URISyntaxException {
		SUBCONTEXT = "mrm";
		return MRMINDEX;
	}

	@RequestMapping(value = { "/pub" }, method = RequestMethod.GET)
	public String getPubIndexPage() throws ParseException, IOException,
			URISyntaxException {
		SUBCONTEXT = "pub";
		return PUBINDEX;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ClientResponse getMrmHome() throws IOException, URISyntaxException {
		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents(SUBCONTEXT
				+ "/home/home.html"));
		customResponse.setEvents(fileUtils.getFileContents(SUBCONTEXT
				+ "/home/events.json"));
		customResponse.setElements(fileUtils.getFileContents(SUBCONTEXT
				+ "/home/elements.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/login" })
	public @ResponseBody
	ClientResponse getLogin() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents(SUBCONTEXT
				+ "/login/login.html"));
		customResponse.setEvents(fileUtils.getFileContents(SUBCONTEXT
				+ "/login/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/header" })
	public @ResponseBody
	ClientResponse getHeader() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents(SUBCONTEXT
				+ "/header/header.html"));
		customResponse.setEvents(fileUtils.getFileContents(SUBCONTEXT
				+ "/header/events.json"));
		return customResponse;

	}

	@RequestMapping(value = { "/footer" })
	public @ResponseBody
	ClientResponse getFooter() throws IOException, URISyntaxException {

		ClientResponse customResponse = new ClientResponse();
		customResponse.setHtml(fileUtils.getFileContents(SUBCONTEXT
				+ "/footer/footer.html"));
		return customResponse;

	}

}
