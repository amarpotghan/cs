package app.cs.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.model.response.ClientResponse;
import app.cs.utils.FileUtils;

@Controller
public class TextAssetController {

	private FileUtils fileUtils;

	@Autowired
	public TextAssetController(FileUtils fileUtils) {
		// TODO Auto-generated constructor stub
		this.fileUtils = fileUtils;
	}

	ClientResponse customResponse = new ClientResponse();

	@RequestMapping(value = { "/text/list/{id}" }, method = RequestMethod.GET)
	public @ResponseBody
	String getTextAssets(@PathVariable String id) throws IOException,
			URISyntaxException {
		return fileUtils.getFileContents("textassets.json");

	}

}